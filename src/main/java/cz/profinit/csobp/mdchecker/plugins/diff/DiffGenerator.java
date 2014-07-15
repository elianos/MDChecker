package cz.profinit.csobp.mdchecker.plugins.diff;

import java.util.List;

import javax.swing.text.html.HTMLDocument;


import javax.swing.text.html.HTMLEditorKit;

import difflib.Delta;
import difflib.Patch;

public class DiffGenerator {

	public String generateHtml(List<String> original, Patch patch, String fileName) {
		StringBuilder builder = new StringBuilder();
		builder.append("<table>");
		builder.append("<thead>");
		builder.append("<tr>");
		builder.append("<th collspan=\"4\">");
		builder.append(fileName);
		builder.append("</th>");
		builder.append("</tr>");
		builder.append("</thead>");

		builder.append("<tbody>");
		
		int i = 0;
		int j = 0;
		for (Delta delta : patch.getDeltas()) {
			int position = delta.getOriginal().getPosition();
			
			while (i < position) {
				builder.append("<tr>");
				builder.append("<td >");
				builder.append(i);
				builder.append("</td>");
				builder.append("<td >");
				builder.append(j);
				builder.append("</td>");
				builder.append("<td></td>");
				builder.append("<td >");
				builder.append(original.get(i));
				builder.append("</td>");
				builder.append("</tr>");

				i++;
				j++;
			}
			
			switch (delta.getType()) {
			case DELETE:
				for (Object line : delta.getOriginal().getLines()) {
					builder.append("<tr style=\"background: #fdd;\">");
					builder.append("<td>");
					builder.append(i++);
					builder.append("</td>");
					builder.append("<td></td>");
					builder.append("<td>-</td>");
					builder.append("<td>");
					builder.append(line);
					builder.append("</td>");
					builder.append("</tr>");
				}
				
				break;
			case INSERT:
				for (Object line : delta.getRevised().getLines()) {
					builder.append("<tr style=\"background: #dfd;\">");
					builder.append("<td></td>");
					builder.append("<td>");
					builder.append(j++);
					builder.append("</td>");
					builder.append("<td>+</td>");
					builder.append("<td>");
					builder.append(line);
					builder.append("</td>");
					builder.append("</tr>");
				}
				
				break;
			case CHANGE:
				for (Object line : delta.getRevised().getLines()) {
					builder.append("<tr style=\"background: #dfd;\">");
					builder.append("<td></td>");
					builder.append("<td>");
					builder.append(j++);
					builder.append("</td>");
					builder.append("<td>+</td>");
					builder.append("<td>");
					builder.append(line);
					builder.append("</td>");
					builder.append("</tr>");
				}
				
				for (Object line : delta.getOriginal().getLines()) {
					builder.append("<tr style=\"background: #fdd;\">");
					builder.append("<td>");
					builder.append(i++);
					builder.append("</td>");
					builder.append("<td></td>");
					builder.append("<td>-</td>");
					builder.append("<td>");
					builder.append(line);
					builder.append("</td>");
					builder.append("</tr>");
				}
				
				break;
			default:
				break;
			}
		}
		
		while (i < original.size()) {
			builder.append("<tr>");
			builder.append("<td >");
			builder.append(i);
			builder.append("</td>");
			builder.append("<td >");
			builder.append(j);
			builder.append("</td>");
			builder.append("<td></td>");
			builder.append("<td >");
			builder.append(original.get(i));
			builder.append("</td>");
			builder.append("</tr>");

			i++;
			j++;
		}
		

		builder.append("</tbody>");
		builder.append("</table>");
		return builder.toString();
	}
}
