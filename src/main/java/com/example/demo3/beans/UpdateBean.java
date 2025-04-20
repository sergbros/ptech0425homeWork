package com.example.demo3.beans;

import com.example.demo3.repositories.entities.AddressEntity;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class UpdateBean implements UpdateService {

    public String check(AddressEntity addressEntity) {

        String errorMessage = "";

        if(!(addressEntity.getClientid().getClientid() > 0)){
             errorMessage += "номер клиента, ";
        }

        if(!(addressEntity.getClientid().getClientName().length() > 0
                && addressEntity.getClientid().getClientName().length() < 101)){
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
            errorMessage += "\n Не корректны! Сохранение невозможно! Проверьте данные.";
            return errorMessage ;
        }
        else return errorMessage;
    }

    public void update(AddressEntity addressEntity) {

    }
}
