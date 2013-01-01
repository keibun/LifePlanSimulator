/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.furusawa.lps.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hiro
 */
public class CapitalSummaryTrack {
//    private List<Asset> assets = new ArrayList<>();
//    private List<Liability> liabilities = new ArrayList<>();
    private int salaryByTrack; // 所得
    private int commulativeSalary; // 累積所得
    private int rentalFee; // 賃貸料
    private int commulativeRentalFee; // 累積賃貸料
    private int houseLoan; // 住宅ローン
    private int commulativeHouseLoan; // 累積住宅ローン
    private int education; // 教育費
    private int commulativeEducation; // 累積教育費
    private int commodity; // 生活費（食費・水道費・光熱費・通信費
    private int commulativeCommodity; // 累積生活費
    private int assetCash;
    
//    public List<Asset> getAssets() {
//        return assets;
//    }
//    
//    public List<Liability> getLiabilities() {
//        return liabilities;
//    }
    
//    public void addAsset(Asset asset){
//        assets.add(asset);
//    }
//    public void addLiability(Liability liability){
//        liabilities.add(liability);
//    }

    /**
     * @return the salaryByTrack
     */
    public int getSalaryByTrack() {
        return salaryByTrack;
    }

    /**
     * @param salaryByTrack the salaryByTrack to set
     */
    public void setSalaryByTrack(int salaryByTrack) {
        this.salaryByTrack = salaryByTrack;
    }

    /**
     * @return the commulativeSalary
     */
    public int getCommulativeSalary() {
        return commulativeSalary;
    }

    /**
     * @param commulativeSalary the commulativeSalary to set
     */
    public void setCommulativeSalary(int commulativeSalary) {
        this.commulativeSalary = commulativeSalary;
    }

    /**
     * @return the rentalFee
     */
    public int getRentalFee() {
        return rentalFee;
    }

    /**
     * @param rentalFee the rentalFee to set
     */
    public void setRentalFee(int rentalFee) {
        this.rentalFee = rentalFee;
    }

    /**
     * @return the commulativeRentalFee
     */
    public int getCommulativeRentalFee() {
        return commulativeRentalFee;
    }

    /**
     * @param commulativeRentalFee the commulativeRentalFee to set
     */
    public void setCommulativeRentalFee(int commulativeRentalFee) {
        this.commulativeRentalFee = commulativeRentalFee;
    }

    /**
     * @return the houseLoan
     */
    public int getHouseLoan() {
        return houseLoan;
    }

    /**
     * @param houseLoan the houseLoan to set
     */
    public void setHouseLoan(int houseLoan) {
        this.houseLoan = houseLoan;
    }

    /**
     * @return the commulativeHouseLoan
     */
    public int getCommulativeHouseLoan() {
        return commulativeHouseLoan;
    }

    /**
     * @param commulativeHouseLoan the commulativeHouseLoan to set
     */
    public void setCommulativeHouseLoan(int commulativeHouseLoan) {
        this.commulativeHouseLoan = commulativeHouseLoan;
    }

    /**
     * @return the education
     */
    public int getEducation() {
        return education;
    }

    /**
     * @param education the education to set
     */
    public void setEducation(int education) {
        this.education = education;
    }

    /**
     * @return the commulativeEducation
     */
    public int getCommulativeEducation() {
        return commulativeEducation;
    }

    /**
     * @param commulativeEducation the commulativeEducation to set
     */
    public void setCommulativeEducation(int commulativeEducation) {
        this.commulativeEducation = commulativeEducation;
    }

    /**
     * @return the commodity
     */
    public int getCommodity() {
        return commodity;
    }

    /**
     * @param commodity the commodity to set
     */
    public void setCommodity(int commodity) {
        this.commodity = commodity;
    }

    /**
     * @return the commulativeCommodity
     */
    public int getCommulativeCommodity() {
        return commulativeCommodity;
    }

    /**
     * @param commulativeCommodity the commulativeCommodity to set
     */
    public void setCommulativeCommodity(int commulativeCommodity) {
        this.commulativeCommodity = commulativeCommodity;
    }

    /**
     * @return the assetCash
     */
    public int getAssetCash() {
        return assetCash;
    }

    /**
     * @param assetCash the assetCash to set
     */
    public void setAssetCash(int assetCash) {
        this.assetCash = assetCash;
    }
}
