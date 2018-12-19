package com.gracenote.logic.implementations;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.gracenote.bean.ResponseBean;
import com.gracenote.logic.WriteInterface;
import com.gracenote.utility.LoggerClass;

public class WriteJson implements WriteInterface {

	@Override
	public boolean writeToFile(ResponseBean responseBean, String path) {
		boolean writeFileResult = true;
		
		// Converting the response to a Pretty JSON
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String responseJson = gson.toJson(responseBean);
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(responseJson);
		String prettyJsonString = gson.toJson(jsonElement);
		
		BufferedWriter writer = null;
		try {

			// Writing the JSON data to the output.json file
			// If user specified output path is invalid, write file to current directory
			if (Files.isDirectory(Paths.get(path))) {
				LoggerClass.logInfoMessages("Valid file path.");
				writer = new BufferedWriter(new FileWriter(path + "output.json"));
			} else {
				LoggerClass.logInfoMessages("Invalid file path.");
				System.out.println("Invalid file path, hence writing file in current directory.");
				writer = new BufferedWriter(new FileWriter("output.json"));
			}

			writer.write(prettyJsonString);
			System.out.println("JSON file written successfully.");
			LoggerClass.logInfoMessages("JSON file written successfully.");

		} catch (IOException e) {
			e.printStackTrace();
			LoggerClass.logErrorMessages(e.getMessage());
			System.out.println("Invalid output path!");
			writeFileResult = false;
		} finally {
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
					LoggerClass.logErrorMessages(e.getMessage());
				}
		}

		return writeFileResult;
	}
}
