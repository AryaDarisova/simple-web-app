package data.servlet;

import com.google.gson.Gson;
import data.model.Item;
import data.model.ItemsDAO;
import data.storage.DAOFactory;
import data.storage.sqlPerRequestDAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;

@WebServlet("/getitems")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        DAOFactory daoFactory = sqlPerRequestDAOFactory.getDAOFactory();
        ItemsDAO itemsDAO = daoFactory.getItemsDAO();
        Stream<Item> items = itemsDAO.getItems().stream();
        if (req.getParameter("id") != null) {
            items = items.filter(item -> item.getId() == Integer.parseInt(req.getParameter("id")));
        }
        if (req.getParameter("name") != null) {
            items = items.filter(item -> item.getName().equals(req.getParameter("name")));
        }
        if (req.getParameter("description") != null) {
            items = items.filter(item -> item.getDescription().equals(req.getParameter("description")));
        }
        if (req.getParameter("cost") != null) {
            items = items.filter(item -> item.getCost() == Double.parseDouble(req.getParameter("cost")));
        }
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(items.toArray()));
        out.flush();

    }
}
