/*
 * Copyright 2016 mam.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.amm_company.pages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import ru.amm_company.LoginPage;
import ru.amm_company.SessionChat;
import ru.amm_company.message.Message;

/**
 *
 * @author mam
 */
public class InputPanel extends Panel {
        public MessagePanel messagePanel;
        private static final int MAX_MESSAGES = 50;
        static private final LinkedList<Message> messages = new LinkedList<Message>();
        static private final List<String> users = new ArrayList<String>(); 

        private MarkupContainer messagesContainer;

        private String name;
        
	public InputPanel(String id, MessagePanel messagePanel) {
		super(id);		
                this.name = SessionChat.get().getUsername();
                if (name == null) {
                        throw new RestartResponseAtInterceptPageException(LoginPage.class);
                }
                this.messagePanel = messagePanel;
                this.users.add(this.name);
		init();
	}
    
	private void init(){
		Form inputForm = new InputForm("inputForm");
		add(inputForm);
	}

        public class InputForm extends Form {
            
                final IModel<String> message = Model.of("");
                
        public InputForm(String id) {
			super(id);
			
		setDefaultModel(new CompoundPropertyModel(this));
                add(new TextArea("message", message));
                // Выводим имена пользователей с чате
                String sTmp = new String();
                for (String s: users) {
                    sTmp += "[" + s + "]" + '\n';
                }
                messagePanel.messageForm.textNames.setObject(sTmp);
                // Выводим очередь сообщений
                sTmp = "";
                synchronized (messages) {
                    for (Message s: messages) {
                        sTmp += s.getMessage();
                    }
                }
                messagePanel.messageForm.textMessage.setObject(sTmp);
	}

        public final void onSubmit() {
                if (message.getObject() != null) {
                    message.setObject(new SimpleDateFormat("[HH:mm:ss] ").format(new Date()) + name + ": " + message.getObject() + "\n");
                    String sTmp = new String();
                    synchronized (messages) {
                        for (Message s: messages) {
                            sTmp += s.getMessage();
                        }
                    }
                    messagePanel.messageForm.textMessage.setObject(sTmp + message.getObject());
                    Message chatMessage = new Message(name, message.getObject());

                    synchronized (messages) {
                        if (messages.size() >= MAX_MESSAGES) {
                            messages.removeFirst();
                        }

                        messages.addLast(chatMessage);
                    }
                    message.setObject("");
                }
        }			
    }
}
