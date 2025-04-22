package com.example.demo3.beans;

import com.example.demo3.DBManager;
import com.example.demo3.repositories.entities.AddressEntity;
import com.example.demo3.repositories.entities.ClientEntity;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ViewListBean implements ViewListService {

    @EJB
    private DBManager dbManager;

    public String viewList(String mode){
        return mode;
    }

    public void viewList(HttpServletRequest request, HttpServletResponse response, String mode) throws ServletException, IOException {
        viewList(request,  response,  mode, "");
    }

    public void viewList(HttpServletRequest request, HttpServletResponse response, String mode, String error) throws IOException, ServletException {
        List<ClientEntity> clientEntities = new ArrayList<>();
        List<AddressEntity> addressEntities = new ArrayList<>();

        PrintWriter out = response.getWriter();

        if(mode != null) {
            switch (mode) {
                default:
                    addressEntities = dbManager.readAllAddressesList();
                    break;
                case "find":
                    //addressEntities = dbManager.readAllUsersByNameOrAddressAndType("Мих", "1");
                    String type;
                    if (request.getParameter("searchClientType").equals("Физическое лицо")) {
                        type = "1";
                    } else {
                        type = "2";
                    }
                    addressEntities = dbManager.readAllUsersByNameOrAddressAndType(request.getParameter("searchName"), type);
                    break;
            }
        }
        else{
            addressEntities = dbManager.readAllAddressesList();
        }

        out.println("<html><head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>ViewList</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Таблица пользователей</h1>");

        //Форма поиска
        RequestDispatcher footerDispatcher = request.getRequestDispatcher("/findForm.jsp");
        footerDispatcher.include(request, response);

        //Таблица
        out.println("<table border=\"0\">");
        //Заголовок
        out.println("<tr>");
        out.println("<td><p align=\"center\">Number</td>");
        out.println("<td bgcolor=\"#C0C0C0\"><p align=\"center\">Name</td>");
        out.println("<td><p align=\"center\">Type</td>");
        out.println("<td bgcolor=\"#C0C0C0\"><p align=\"center\">Address</td>");
        out.println("<td><p align=\"center\">Edit</td>");
        out.println("<td bgcolor=\"#C0C0C0\"><p align=\"center\">Удаление</td>");
        out.println("</tr>");

        //Строка
        for (AddressEntity addr : addressEntities) {
            ClientEntity cl = (addr.getClientid());
            out.println("<tr>");
            out.println("<td>" + cl.getClientid() + "</td>");
            out.println("<td bgcolor=\"#C0C0C0\">" + cl.getClientName() + "</td>");
            if (Integer.parseInt(cl.getType()) == 1) {
                out.println("<td>" + "Физическое лицо" + "</td>");
            } else {
                out.println("<td>" + "Юридическое лицо" + "</td>");
            }
            out.println("<td bgcolor=\"#C0C0C0\">" + addr.getPostaddress() + "</td>");
            out.println("<td>" + "<a href=\"update?clientid=" + cl.getClientid() + "&addr=" + addr.getAddressid() + "&key=none\">[ Редактировать…]</a>" + "</td>");
            out.println("<td>" + "<a href=\"update?clientid=" + cl.getClientid() + "&addr=" + addr.getAddressid() + "&key=addaddress\">[ Добавить адрес…]</a>" + "</td>");
            out.println("<td>" + "<a href=\"delete?clientid=" + cl.getClientid() + "&addr=" + addr.getAddressid() + "\">[ Удалить ]</a>" + "</td>");
            out.println("</tr>");
        }

        //конец таблицы
        out.println("</table>");

        RequestDispatcher footerDispatcherBottom = request.getRequestDispatcher("/menu.jsp");
        footerDispatcherBottom.include(request, response);

        out.println("</body></html>");
    }
}
