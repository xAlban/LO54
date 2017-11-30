/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burattoelezi.lo54projet.core.entity;

import com.burattoelezi.lo54projet.core.service.ClientService;

/**
 *
 * @author aelez
 */
public class AppTest {
    
    private ClientService service = new ClientService();
    private Client client = new Client();
    public void main(){
        client = service.getInfoClient(2);
        System.out.println(client.getFirstName());
    }
}
