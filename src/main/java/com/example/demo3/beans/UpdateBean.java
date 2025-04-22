package com.example.demo3.beans;

import com.example.demo3.repositories.entities.AddressEntity;
import jakarta.ejb.Stateless;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Stateless
public class UpdateBean implements UpdateService {

    public String check(AddressEntity addressEntity) {

        String errorMessage = "";

        if(!(addressEntity.getClientid().getClientid() > 0)){
             errorMessage += "номер клиента, ";
        }

        String regexClientName = "^[а-яА-ЯёЁ\\s.,-]+$";
        if(!(addressEntity.getClientid().getClientName().length() > 0
                && addressEntity.getClientid().getClientName().length() < 101
        && addressEntity.getClientid().getClientName().matches(regexClientName))){
            errorMessage += "имя клиента, ";
        }

        if(!(addressEntity.getClientid().getType().length() > 0
                && addressEntity.getClientid().getType().length() < 21)){
            errorMessage += "тип клиента, ";
        }

        if(!(addressEntity.getClientid().getAdded() != null)){
            errorMessage += "дата добавления, ";
        }

        String ipRegex = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
                "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        if(!(addressEntity.getIp() != null
                && addressEntity.getIp().length() > 0
                && addressEntity.getIp().length() < 26
                && addressEntity.getIp().matches(ipRegex))){
            errorMessage += "IP адрес, ";
        }

        //строка с любыми разделителями (двоеточие, дефис)
        String macRegex = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$";
        if(!(addressEntity.getMac() != null
                && addressEntity.getMac().length() > 0
                && addressEntity.getMac().length() < 21
                && addressEntity.getMac().matches(macRegex))){
            errorMessage += "MAC адрес, ";
        }

        if(!(addressEntity.getModel() != null
                && addressEntity.getModel().length() > 0
                && addressEntity.getModel().length() < 101)){
            errorMessage += "модель устройства, ";
        }

        if(!(addressEntity.getPostaddress() != null
                && addressEntity.getPostaddress().length() > 0
                && addressEntity.getPostaddress().length() < 201)){
            errorMessage += "почтовый адрес, ";
        }

        if(errorMessage.length() > 0){
            errorMessage = "Поля: " + errorMessage + " заполнены некорректно! Сохранение невозможно!";
            return errorMessage ;
        }
        else return errorMessage;
    }

    public void update(HttpServletRequest request, HttpServletResponse response, AddressEntity addressEntity) throws IOException {
        if(request.getParameter("key").equals("addaddress")){
            //Добавление адреса к уже имеющимся
            PrintWriter writer = response.getWriter();
            String contextPath = request.getContextPath();
            writer.println("<form action=\"" + contextPath + "/create?clientid="+ addressEntity.getClientid().getClientid() + "&key=addaddress\" method=\"POST\">");
            writer.println("<p>Имя: <input disabled type=\"text\" size=\"20\" name=\"name\" value=\"" + addressEntity.getClientid().getClientName() + "\"></p>");
            writer.println("<p>Тип клиента: <select name=\"clientType\" size=\"1\">");
            if (addressEntity.getClientid().getType().equals("1")) {
                writer.println("<option selected>Физическое лицо</option>");
                writer.println("<option>Юридическое лицо</option>");
            } else {
                writer.println("<option>Физическое лицо</option>");
                writer.println("<option selected>Юридическое лицо</option>");
            }
            writer.println("</select></p>");

            writer.println("<p><input disabled type=\"text\" size=\"25\" name=\"clientid\" hidden value=\"" + addressEntity.getClientid().getClientid() + "\"></p>");
            writer.println("<p><input disabled type=\"text\" size=\"25\" name=\"added\" hidden value=\"" + addressEntity.getClientid().getAdded() + "\"></p>");

            writer.println("<p>Сетевой IP адрес устройства: <input type=\"text\" size=\"25\" name=\"ipAddress\"></p>");
            writer.println("<p>Физический адрес устройства (MAC): <input type=\"text\" size=\"25\" name=\"macAddress\"></p>");
            writer.println("<p>Модель устройства: <input type=\"text\" size=\"25\" name=\"model\"></p>");
            writer.println("<p>Адрес места нахождения: <input type=\"text\" size=\"25\" name=\"postaddress\"></p>");
            writer.println("<p><input type=\"submit\" name=\"Edit\" value=\"Добавить адрес\"></p>");
            writer.println("</form>");
        }
        else {
            PrintWriter writer = response.getWriter();
            String contextPath = request.getContextPath();
            writer.println("<form action=\"" + contextPath + "/update?key=none\" method=\"POST\">");
            writer.println("<p>Имя: <input type=\"text\" size=\"20\" name=\"name\" value=\"" + addressEntity.getClientid().getClientName() + "\"></p>");
            writer.println("<p>Тип клиента: <select name=\"clientType\" size=\"1\">");
            if (addressEntity.getClientid().getType().equals("1")) {
                writer.println("<option selected>Физическое лицо</option>");
                writer.println("<option>Юридическое лицо</option>");
            } else {
                writer.println("<option>Физическое лицо</option>");
                writer.println("<option selected>Юридическое лицо</option>");
            }
            writer.println("</select></p>");

            writer.println("<p><input type=\"text\" size=\"25\" name=\"clientid\" hidden value=\"" + addressEntity.getClientid().getClientid() + "\"></p>");
            writer.println("<p><input type=\"text\" size=\"25\" name=\"added\" hidden value=\"" + addressEntity.getClientid().getAdded() + "\"></p>");
            writer.println("<p>Сетевой IP адрес устройства: <input type=\"text\" size=\"25\" name=\"ipAddress\" value=\"" + addressEntity.getIp() + "\"></p>");
            writer.println("<p>Физический адрес устройства (MAC): <input type=\"text\" size=\"25\" name=\"macAddress\" value=\"" + addressEntity.getMac() + "\"></p>");
            writer.println("<p>Модель устройства: <input type=\"text\" size=\"25\" name=\"model\" value=\"" + addressEntity.getModel() + "\"></p>");
            writer.println("<p>Адрес места нахождения: <input type=\"text\" size=\"25\" name=\"postaddress\" value=\"" + addressEntity.getPostaddress() + "\"></p>");
            writer.println("<p><input type=\"text\" size=\"25\" name=\"addressid\" hidden value=\"" + addressEntity.getAddressid() + "\"></p>");
            writer.println("<p><input type=\"submit\" name=\"Edit\" value=\"Применить изменения\"></p>");
            writer.println("</form>");
        }
    }
}
