package cz.profinit.csobp.mdchecker.plugins.diff;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.profinit.csobp.mdchecker.utils.FileManipulatorUtil;
import difflib.Delta;
import difflib.Patch;

/**
 * Generator vystupu pro rozdil mezi dvemi soubory
 * 
 * @author: vjinoch
 */
public class DiffGenerator {
	
	/**
	 * uloziste css tylu pro rozdil
	 */
	private final static String STYLE_FILE = "assets/style/style.html";
	
	/**
	 * Generuje diff html vystup.
	 * 
	 * @param original puvodni radky souboru.
	 * @param patch patch na novou verzi.
	 * @param fileName nazev meneneho souboru
	 * 
	 * @return html diff.
	 */
	public String generateHtml(List<String> original, Patch patch,
			String fileName) {
		StringBuilder builder = new StringBuilder();
		builder.append("<!DOCTYPE html>");
		builder.append("<html>");
		builder.append("<head>");
		try {
			builder.append(loadStyle());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		builder.append("</head>");
		builder.append("<body>");
		
		builder.append(generateBody(original, patch, fileName));
		builder.append("</body>");
		builder.append("</html>");
		
		return builder.toString();
	}
	
	/**
	 * Generuje telo html diffu.
	 * 
	 * @param original puvodni radky souboru.
	 * @param patch patch na novou verzi.
	 * @param fileName nazev meneneho souboru
	 * 
	 * @return telo html diffu.
	 */
	public String generateBody(List<String> original, Patch patch,
			String fileName) {
		StringBuilder builder = new StringBuilder();

		generateHeader(builder, fileName);

		int oldLine = 0;
		int newLine = 0;
		for (Delta delta : patch.getDeltas()) {
			int position = delta.getOriginal().getPosition();
			while (oldLine < position) {
				generateLine(builder, ERowType.NILL, oldLine, newLine, original.get(oldLine));
				oldLine++;
				newLine++;
			}

			switch (delta.getType()) {
			case DELETE:
				for (Object line : delta.getOriginal().getLines()) {
					generateLine(builder, ERowType.DELETE, oldLine, newLine,
							line.toString());
					oldLine++;
				}

				break;
			case INSERT:
				for (Object line : delta.getRevised().getLines()) {
					generateLine(builder, ERowType.INSERT, oldLine, newLine,
							line.toString());
					newLine++;
				}

				break;
			case CHANGE:
				for (Object line : delta.getRevised().getLines()) {
					generateLine(builder, ERowType.INSERT, oldLine, newLine,
							line.toString());
					newLine++;
				}

				for (Object line : delta.getOriginal().getLines()) {
					generateLine(builder, ERowType.DELETE, oldLine, newLine,
							line.toString());
					oldLine++;
				}

				break;
			default:
				break;
			}
		}

		while (oldLine < original.size()) {
			generateLine(builder, ERowType.NILL, oldLine, newLine, original.get(oldLine));
			oldLine++;
			newLine++;
		}

		generateFooter(builder, fileName);

		return builder.toString();
	}

	/**
	 * Generuje zahlavi diffu.
	 * 
	 * @param builder pro vygenerovani zahlavi.
	 * @param fileName nazev souboru.
	 */
	private void generateHeader(StringBuilder builder, String fileName) {
		builder.append("<table>");
		builder.append("<thead>");
		builder.append("<tr class=\"fh\">");
		builder.append("<th colspan=\"4\">");
		builder.append(fileName);
		builder.append("</th>");
		builder.append("</tr>");
		builder.append("</thead>");

		builder.append("<tbody>");
	}

	/**
	 * Generuje zapati diffu.
	 * 
	 * @param builder pro vygenerovani zapati.
	 * @param fileName nazev souboru.
	 */
	private void generateFooter(StringBuilder builder, String fileName) {
		builder.append("</tbody>");
		builder.append("<tfoot>");
		builder.append("<tr class=\"fh\">");
		builder.append("<td colspan=\"4\">");
		builder.append(fileName);
		builder.append("</td>");
		builder.append("</tr>");
		builder.append("</tfoot>");

		builder.append("<tbody>");

		builder.append("</tbody>");
		builder.append("</table>");
	}

	/**
	 * Generuje html radek diffu.
	 * 
	 * @param builder builder pro pripojeni radku.
	 * @param type radku. INSERT -> pridani, DELETE -> odebrani, NILL -> klasicky radek.
	 * @param oldLine cislo puvodniho radek.
	 * @param newLine cislo noveho radek.
	 * @param value hodnota radku.
	 */
	private void generateLine(StringBuilder builder, ERowType type,
			int oldLine, int newLine, String value) {
		String rowName = "";
		String symbol = "";

		switch (type) {
		case INSERT:
			rowName = "insert";
			symbol = "+";
			break;
		case DELETE:
			rowName = "delete";
			symbol = "-";
			break;
		case NILL:
			rowName = "";
			symbol = "";
			break;
		default:
			break;
		}

		builder.append("<tr class=\"" + rowName + "\">");
		builder.append("<td class=\"line\">");
		builder.append(type == ERowType.INSERT ? "" : oldLine);
		builder.append("</td>");
		builder.append("<td class=\"line\">");
		builder.append(type == ERowType.DELETE ? "" : newLine);
		builder.append("</td>");
		builder.append("<td class=\"action\">" + symbol + "</td>");
		builder.append("<td class=\"code\">");
		builder.append("<pre>" + value + "</pre>");
		builder.append("</td>");
		builder.append("</tr>");
	}

	/**
	 * Nacteni stylu.
	 * 
	 * @return vraci html s css styly.
	 * @throws IOException
	 */
	private String loadStyle() throws IOException {
		return FileUtils.readFileToString(FileManipulatorUtil.getResourceFile(STYLE_FILE));
	}
	
	/**
	 * Enum s typy radku v diff filu.
	 * 
	 * @author: vjinoch
	 */
	private enum ERowType {
		INSERT, DELETE, NILL;
	}
}
