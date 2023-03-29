package com.etc.vo;

public class Goods {
	private String price;
    private String name;
    private String kaifashang;
    private String describe1;
    private String photo;
    private String type;
    private String discount;
    private String faxingshang;
    private String shiping;
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKaifashang() {
		return kaifashang;
	}
	public void setKaifashang(String kaifashang) {
		this.kaifashang = kaifashang;
	}
	public String getDescribe1() {
		return describe1;
	}
	public void setDescribe1(String describe1) {
		this.describe1 = describe1;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getFaxingshang() {
		return faxingshang;
	}
	public void setFaxingshang(String faxingshang) {
		this.faxingshang = faxingshang;
	}
	public String getShiping() {
		return shiping;
	}
	public void setShiping(String shiping) {
		this.shiping = shiping;
	}
	@Override
	public String toString() {
		return "Goods [price=" + price + ", name=" + name + ", kaifashang=" + kaifashang + ", describe1=" + describe1
				+ ", photo=" + photo + ", type=" + type + ", discount=" + discount + ", faxingshang=" + faxingshang
				+ ", shiping=" + shiping + "]";
	}
	
}
