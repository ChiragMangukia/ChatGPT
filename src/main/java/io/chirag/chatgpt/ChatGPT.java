package io.chirag.chatgpt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.json.JSONObject;

import io.chirag.util.Utilities;

public class ChatGPT {

	public static String askMe(String query) {

		Properties prop;

		String url = "https://api.openai.com/v1/completions";

		HttpURLConnection con;

		try {
			prop = Utilities.getProp();
			con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Authorization", "Bearer " + prop.getProperty("apikey").trim());

			JSONObject data = new JSONObject();
			data.put("model", "text-davinci-003");
			data.put("prompt", query);
			data.put("max_tokens", 4000);
			data.put("temperature", 1.0);

			con.setDoOutput(true);
			con.getOutputStream().write(data.toString().getBytes());

			String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
					.reduce((a, b) -> a + b).get();

			return new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text").trim();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
