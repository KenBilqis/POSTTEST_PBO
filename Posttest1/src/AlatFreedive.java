import java.util.ArrayList;

class AlatFreedive {

    private static int counter = 1;

    private String idAlat;
    private String namaAlat;
    private String merk;
    private String kondisi;

    AlatFreedive(String namaAlat, String merk, String kondisi) {
        this.idAlat   = String.format("%03d", counter);
        counter++;
        this.namaAlat = namaAlat;
        this.merk     = merk;
        this.kondisi  = kondisi;
    }

    String getIdAlat() {
        return this.idAlat;
    }

    String getNamaAlat() {
        return this.namaAlat;
    }

    String getMerk() {
        return this.merk;
    }

    String getKondisi() {
        return this.kondisi;
    }

    void setNamaAlat(String namaAlat) {
        this.namaAlat = namaAlat;
    }

    void setMerk(String merk) {
        this.merk = merk;
    }

    void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }
    static void renumberIds(ArrayList<AlatFreedive> list) {
        for (int j = 0; j < list.size(); j++) {
            list.get(j).idAlat = String.format("%03d", j + 1);
        }
    }
}