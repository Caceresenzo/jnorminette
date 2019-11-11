package caceresenzo.apps.jnorminette;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import caceresenzo.apps.jnorminette.rules.RuleManager;

public class Bootstrap {

	public static void main(String[] args) throws IOException {
		String path = "C:\\Users\\cacer\\OneDrive\\Developments\\Workspace 42\\ft_printf";
		
		File folder = new File(path);
		for (File file : folder.listFiles((dir, name) -> name.endsWith(".c"))) {
			List<String> lines = Files.readAllLines(file.toPath());
			String rawLines = String.join("\n", lines);
			
			System.out.println("file: " + file.getPath());
			RuleManager.get().findErrors(lines, rawLines).forEach((error) -> {
				System.out.println(error.format());
			});
		}
	}
	
}