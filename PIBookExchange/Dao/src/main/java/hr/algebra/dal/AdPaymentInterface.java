/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.algebra.dal;


public interface AdPaymentInterface extends Repository{

    int getPaymentIdByName(String paymentName) throws Exception;

}