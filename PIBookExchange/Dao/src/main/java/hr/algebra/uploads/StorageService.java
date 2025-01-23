/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.algebra.uploads;

/**
 *
 * @author Tin
 */
public interface StorageService {
    void uploadFile(String localPath, String remotePath) throws Exception;
}