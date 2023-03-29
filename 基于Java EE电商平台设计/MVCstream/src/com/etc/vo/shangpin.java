package com.etc.vo;
//实体类
public class shangpin {
private String name;
private int  price ;
private String describe1;
private String photo ;
private String discount ;
private String type ;
//详细信息
private String shiping;
private String kaifashang;
private String faxingshang;
private String typec ;
	


public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public String getDescribe1() {
	return describe1;
}
public void setDescribe1(String describe) {
	this.describe1 = describe;
}
public String getPhoto() {
	return photo;
}
public void setPhoto(String photo) {
	this.photo = photo;
}
public String getDiscount() {
	return discount;
}
public void setDiscount(String discount) {
	this.discount = discount;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getShiping() {
	return shiping;
}
public void setShiping(String shiping) {
	this.shiping = shiping;
}
public String getKaifashang() {
	return kaifashang;
}
public void setKaifashang(String kaifashang) {
	this.kaifashang = kaifashang;
}
public String getFaxingshang() {
	return faxingshang;
}
public void setFaxingshang(String faxingshang) {
	this.faxingshang = faxingshang;
}

public String getTypec() {
	return typec;
}
public void setTypec(String typec) {
	this.typec = typec;
}
@Override
public String toString() {
	return "shangpin [name=" + name + ", price=" + price + ", describe=" + describe1 + ", photo=" + photo + ", discount="
			+ discount + ", type=" + type + "]";
}

}
