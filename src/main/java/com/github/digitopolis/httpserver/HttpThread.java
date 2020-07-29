package com.github.digitopolis.httpserver;

import com.github.digitopolis.httpserver.cli.CLI;
import com.github.digitopolis.httpserver.parser.RequestParser;
import com.github.digitopolis.httpserver.request.HttpRequest;
import com.github.digitopolis.httpserver.response.HTTPResponse;
import com.github.digitopolis.httpserver.response.ResponseFormatter;
import com.github.digitopolis.httpserver.router.Router;
import com.github.digitopolis.httpserver.validator.InputValidator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpThread extends Thread {
    private Socket socket;
    private CLI cli = new CLI();
    private ResponseFormatter formatter = new ResponseFormatter();

    public HttpThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
                ){
            cli.printMessage("Client connected");
            String request = in.readLine();
            cli.printMessage(request);
            if (request != null) {
                HttpRequest parsedRequest = RequestParser.parseInput(request);
                HTTPResponse response = Router.handleRequest(parsedRequest);
                cli.printMessage(response.getStatusLine());
                out.println(formatter.format(parsedRequest.method, response));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
