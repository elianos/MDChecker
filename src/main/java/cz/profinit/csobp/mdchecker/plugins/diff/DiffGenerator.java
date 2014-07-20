package cz.profinit.csobp.mdchecker.plugins.diff;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.apache.commons.io.FileUtils;

import difflib.Delta;
import difflib.Delta.TYPE;
import difflib.Patch;

public class DiffGenerator {
	
	private final static String STYLE_FILE = "assets/style/style.html";
	
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

	private String loadStyle() throws IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL url = classLoader.getResource(STYLE_FILE);
		return FileUtils.readFileToString(new File(url.getPath()));
	}
	
	private enum ERowType {
		INSERT, DELETE, NILL;
	}
}
