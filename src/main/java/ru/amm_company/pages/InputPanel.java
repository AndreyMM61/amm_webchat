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
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.time.Duration;
import ru.amm_company.LoginPage;
import ru.amm_company.SessionChat;
import ru.amm_company.message.Message;

/**
 *
 * @author mam
 */
public class InputPanel extends Panel {
        private static final int MAX_MESSAGES = 20;
        private static final LinkedList<Message> LIST_MESSAGES = new LinkedList<Message>();
        private static final List<String> LIST_USERS = new ArrayList<String>(); 

        private WebMarkupContainer messagesContainer;
        private String name;
        private String OUT_MESSAGES;
        private String OUT_NAMES;
        
	public InputPanel(String id) {
		super(id);		
                this.name = SessionChat.get().getUsername();
                if (this.name == null) {
                        throw new RestartResponseAtInterceptPageException(LoginPage.class);
                }
                boolean nameExists = false;
                for (String s: LIST_USERS) {
                    if (s == this.name) nameExists = true;
                }
                if (!nameExists) {
                    this.LIST_USERS.add(this.name);
                }
		init();
	}
    
	private void init(){
		Form inputForm = new InputForm("inputForm", this.name);
		add(inputForm);
	}
        
                @Override
        protected void finalize() throws Throwable {
                LIST_USERS.remove(name);
                super.finalize(); //To change body of generated methods, choose Tools | Templates.
        }

        public class InputForm extends Form {
            
                final IModel<String> username = Model.of("");
                final IModel<String> message = Model.of("");
    		final IModel<String> textMessage = Model.of("");
		final IModel<String> textNames = Model.of("");
            
        public InputForm(String id, String name) {
		super(id);
			
                messagesContainer = new WebMarkupContainer("messages");
                    
                this.username.setObject(name);
		setDefaultModel(new CompoundPropertyModel(this));
                messagesContainer.setOutputMarkupId(true);
                messagesContainer.add(new Label("textMessage", textMessage));
                messagesContainer.add(new Label("textNames", textNames));
               
                messagesContainer.add(new AbstractAjaxTimerBehavior(Duration.seconds(1)) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    protected void onTimer(AjaxRequestTarget target) {
                        // Выводим имена пользователей с чате
                        synchronized (LIST_USERS) {
                            OUT_NAMES = "";
                            for (String s: LIST_USERS) {
                                OUT_NAMES += "[" + s + "]" + '\n';
                            }
                            textNames.setObject(OUT_NAMES);
                        }
                        // Выводим очередь сообщений
                        synchronized (LIST_MESSAGES) {
                            OUT_MESSAGES = "";
                            for (Message s: LIST_MESSAGES) {
                                OUT_MESSAGES += s.getMessage();
                            }
                            textMessage.setObject(OUT_MESSAGES);
                        }
                        target.add(messagesContainer);
                   }
                });
                
                add(new Label("username", username));
                add(new TextField("message", message));
                add(messagesContainer);
	}

                @Override
        public final void onSubmit() {
                if (message.getObject() != null) {
                    message.setObject(new SimpleDateFormat("[HH:mm:ss] ").format(new Date()) + name + ": " + message.getObject() + "\n");

                    Message chatMessage = new Message(name, message.getObject());

                    synchronized (LIST_MESSAGES) {
                        if (LIST_MESSAGES.size() >= MAX_MESSAGES) {
                            LIST_MESSAGES.removeFirst();
                        }

                        LIST_MESSAGES.addLast(chatMessage);

                        OUT_MESSAGES = "";
                        for (Message s: LIST_MESSAGES) {
                            OUT_MESSAGES += s.getMessage();
                        }
                        textMessage.setObject(OUT_MESSAGES);
                    }

                    message.setObject("");
                }
        }			
    }
}
