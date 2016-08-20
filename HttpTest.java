package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpTest {

	public void sendHttp(String url, String params) {
		System.out.println(url);
		System.out.println(params);

		URL u;
		try {
			u = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		}

		try {
			HttpURLConnection connection = (HttpURLConnection) u.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "text/html");
			connection.setRequestProperty("Accept", "application/json");
			connection.setDoOutput(true);

			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			writer.write(params);
			writer.flush();
			writer.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response);

			connection.disconnect();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
