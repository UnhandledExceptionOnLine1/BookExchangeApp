/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.Ad;
import hr.algebra.model.AdDetails;
import hr.algebra.model.AllAdsBasic;
import java.util.List;
import java.util.Optional;


public interface AdRepositoryInterface extends Repository {

    int createAd(Ad ad) throws Exception;

    void updateAd(int id, Ad ad) throws Exception;

    void deleteAd(int id) throws Exception;

    Optional<AdDetails> getAd(int id) throws Exception;

    List<AllAdsBasic> getAllAdsBasic() throws Exception;

}
