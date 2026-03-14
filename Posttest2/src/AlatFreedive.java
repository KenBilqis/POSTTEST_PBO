import java.util.ArrayList;

class AlatFreedive {

    // private
    private static int counter = 1;
    private String idAlat;
    private String namaAlat;
    private String merk;
    private String kondisi;

    AlatFreedive(String namaAlat, String merk, String kondisi) {
        this.idAlat = String.format("%03d", counter);
        counter++;
        this.namaAlat = namaAlat;
        this.merk = merk;
        this.kondisi = kondisi;
    }

    // getter - public
    public String getIdAlat() {
        return this.idAlat;
    }

    public String getNamaAlat() {
        return this.namaAlat;
    }

    public String getMerk() {
        return this.merk;
    }

    public String getKondisi() {
        return this.kondisi;
    }

    // setter - public
    public void setNamaAlat(String namaAlat) {
        if (namaAlat == null || namaAlat.isEmpty()) {
            System.out.println("[!] Nama alat tidak boleh kosong.");
        } else {
            this.namaAlat = namaAlat;
        }
    }

    public void setMerk(String merk) {
        if (merk == null || merk.isEmpty()) {
            System.out.println("[!] Merk tidak boleh kosong.");
        } else {
            this.merk = merk;
        }
    }

    public void setKondisi(String kondisi) {
        if (kondisi == null || kondisi.isEmpty()) {
            System.out.println("[!] Kondisi tidak boleh kosong.");
        } else {
            this.kondisi = kondisi;
        }
    }

    // setter - package-private
    void setIdAlat(String id) {
        this.idAlat = id;
    }

    // getter - protected
    protected String getRingkasan() {
        return this.idAlat + " | " + this.namaAlat + " | " + this.merk + " | " + this.kondisi;
    }

    // package-private
    static void renumberIds(ArrayList<AlatFreedive> list) {
        for (int j = 0; j < list.size(); j++) {
            list.get(j).setIdAlat(String.format("%03d", j + 1));
        }
        counter = list.size() + 1;
    }
}