package org.example.simple;

import org.example.data.DataReaderr;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class simpleBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "cbarx_bot";
    }

    @Override
    public String getBotToken() {
        return "5636716136:AAHa37DwRvxxXN7hr4GskeuHz8BxL6_BM_k";
    }


    @Override
    public void onUpdateReceived(Update update) {
//        System.out.println(update.getMessage().getText());
//        System.out.println(update.getMessage().getFrom().getFirstName());
        String command = update.getMessage().getText();
        DataReaderr dataReaderr = new DataReaderr();
        SendMessage response = new SendMessage();
        if(command.equals("/run")){
            response.setChatId(update.getMessage().getChatId().toString());
            try {
                response.setText(dataReaderr.getResponse().toString());
            } catch (IOException | ParserConfigurationException | SAXException e) {
             e.printStackTrace();
            }
            try{
                execute(response);
            }catch(Exception e){
            e.printStackTrace();
            }
        }
    }

}
