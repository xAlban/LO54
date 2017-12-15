/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fburatto
 */
public class VerifyFormField {
    private boolean  validMC;
    private boolean  validDD;
    private boolean  validDF;

    
    public VerifyFormField(){
        validMC=false;
        validDD=false;
        validDF=false;
    }

    /**
     * @param validMC the validMC to set
     */
    public void setValidMC(boolean validMC) {
        this.validMC = validMC;
    }

    /**
     * @param validDD the validDD to set
     */
    public void setValidDD(boolean validDD) {
        this.validDD = validDD;
    }

    /**
     * @param validDF the validDF to set
     */
    public void setValidDF(boolean validDF) {
        this.validDF = validDF;
    }

    /**
     * @return the validMC
     */
    public boolean isValidMC() {
        return validMC;
    }

    /**
     * @return the validDD
     */
    public boolean isValidDD() {
        return validDD;
    }

    /**
     * @return the validDF
     */
    public boolean isValidDF() {
        return validDF;
    }

    
   

}
