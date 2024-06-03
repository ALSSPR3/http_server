package ch01;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;

import javax.swing.text.AbstractDocument.Content;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class SelfSimpleHttpServer {

	public static void main(String[] args) {

		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
			server.createContext("/test", new TestHendler());

			server.start();
			System.out.println("서버 시작");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static class TestHendler implements HttpHandler {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			String method = exchange.getRequestMethod();
			System.out.println(method);

			if ("GET".equalsIgnoreCase(method)) {
				HandleGetRequest(exchange);
			} else if ("PORT".equalsIgnoreCase(method)) {

			}
		} // end of handle

		private void HandleGetRequest(HttpExchange exchange) throws IOException {
			String response = "안녕!";
			exchange.sendResponseHeaders(200, response.length());
			OutputStream os = exchange.getResponseBody();
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, "UTF-8"), true);

			writer.print(response);
			writer.close();
		}
	}
}
