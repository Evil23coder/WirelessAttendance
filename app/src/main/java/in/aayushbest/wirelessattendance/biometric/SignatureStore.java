package in.aayushbest.wirelessattendance.biometric;

public interface SignatureStore {
    public void storePublicKey(String uniqueID);
    public String getPublicKey();
}
