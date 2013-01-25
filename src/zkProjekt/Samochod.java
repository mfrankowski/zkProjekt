package zkProjekt;

import java.util.Date;

public class Samochod implements Cloneable {
	private String marka;
	private String numRej;
	private Date dataP;
	private Date dataU;
	
    public String getMarka() {
		return marka;
	}



	public void setMarka(String marka) {
		this.marka = marka;
	}



	public String getNumRej() {
		return numRej;
	}



	public void setNumRej(String numRej) {
		this.numRej = numRej;
	}



	public Date getDataP() {
		return dataP;
	}



	public void setDataP(Date dataP) {
		this.dataP = dataP;
	}



	public Date getDataU() {
		return dataU;
	}



	public void setDataU(Date dataU) {
		this.dataU = dataU;
	}



	public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
