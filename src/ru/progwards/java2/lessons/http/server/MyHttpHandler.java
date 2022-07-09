package ru.progwards.java2.lessons.http.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.lang3.StringEscapeUtils;
import ru.progwards.java2.lessons.http.server.app.service.AccountService;
import ru.progwards.java2.lessons.http.server.app.service.StoreService;
import ru.progwards.java2.lessons.http.server.app.service.impl.AccountServiceImpl;
import ru.progwards.java2.lessons.http.server.app.service.impl.StoreServiceImpl;

import java.io.IOException;
import java.io.OutputStream;

public class MyHttpHandler implements HttpHandler {

    private AccountService accountService = new AccountServiceImpl();
    private StoreService storeService = new StoreServiceImpl();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String[] requestParamValue=null;

        if("GET".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(httpExchange);
        }

        handleResponse(httpExchange,requestParamValue);
    }
        /*GET /resource?param1=value1&param2=value2 HTTP/1.1
        hostname: localhost
        пустая строка*/
        private String[] handleGetRequest(HttpExchange httpExchange) {
            return httpExchange.getRequestURI().toString().split("\\?")[1].split("&");
        }

    private String[]  handlePostRequest (HttpExchange httpExchange) {
        return new String[0];
    }

    private void handleResponse(HttpExchange httpExchange, String[] requestParamValue)  throws  IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();
        double balance = 0.0;
        switch (requestParamValue[0]) {
            case  ("balance"):
                balance = accountService.balance(storeService.get(requestParamValue[1].split("=")[1]));
                break;

            default:
                System.out.println("do nothing");
                break;
        }

        htmlBuilder.append("\n").
                append("\n").
                append("<h1>").
                append("Hello ")
                .append("\n")
                .append(balance)
                .append("</h1>")
                .append("")
                .append("");

        // encode HTML content
        String htmlResponse = StringEscapeUtils.unescapeHtml4(htmlBuilder.toString());

        // this line is a must
        httpExchange.sendResponseHeaders(200, htmlBuilder.length());

        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
