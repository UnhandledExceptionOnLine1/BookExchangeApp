/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.Ad;
import hr.algebra.model.AdDetails;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author bruno
 */
public interface AdRepositoryInterface {

    void createAd(Ad ad) throws Exception;

    void updateAd(int id, Ad ad) throws Exception;

    void deleteAd(int id) throws Exception;

    Optional<AdDetails> getAd(int id) throws Exception;

    List<Ad> getAllAds() throws Exception;
}
