import java.util.ArrayList;
import java.util.Scanner;

class Main {

    // private
    private static ArrayList<AlatFreedive> inventaris = new ArrayList<AlatFreedive>();

    // private
    private static void pesanDataKosong() {
        System.out.println("\n===========================");
        System.out.println("  Inventaris masih kosong");
        System.out.println("===========================");
    }

    // private
    private static void cetakGaris() {
        System.out.println("+----------+----------------------+------------------+---------------+");
    }

    // private
    private static void cetakHeader() {
        cetakGaris();
        System.out.println("| ID       | Nama Alat            | Merk             | Kondisi       |");
        cetakGaris();
    }

    // private
    private static String formatKolom(String teks, int lebar) {
        if (teks.length() > lebar) return teks.substring(0, lebar);
        return teks + " ".repeat(lebar - teks.length());
    }

    // private
    private static String pilihKondisi(Scanner input) {
        while (true) {
            System.out.println("Pilih Kondisi Alat  :");
            System.out.println("  1. Bagus");
            System.out.println("  2. Cukup Bagus");
            System.out.println("  3. Kurang");
            System.out.print("Pilihan (1/2/3)     : ");
            String pilihan = input.nextLine();
            switch (pilihan) {
                case "1": return "Bagus";
                case "2": return "Cukup Bagus";
                case "3": return "Kurang";
                default: System.out.println("[!] Pilihan tidak valid.");
            }
        }
    }

    // getter - private
    private static ArrayList<AlatFreedive> getInventaris() {
        return inventaris;
    }

    // getter - private
    private static int getTotalAlat() {
        return inventaris.size();
    }

    // public
    public static void tambahAlat(Scanner input) {
        System.out.println("\n========================================");
        System.out.println("       TAMBAH ALAT FREEDIVE BARU");
        System.out.println("========================================");

        System.out.print("Masukkan Nama Alat  : ");
        String nama = input.nextLine();

        System.out.print("Masukkan Merk       : ");
        String merk = input.nextLine();

        String kondisi = pilihKondisi(input);

        AlatFreedive alatBaru = new AlatFreedive(nama, merk, kondisi);
        getInventaris().add(alatBaru);

        System.out.println("\n====================================================");
        System.out.println("  Alat '" + alatBaru.getNamaAlat() + "' berhasil ditambahkan dengan ID " + alatBaru.getIdAlat());
        System.out.println("====================================================");
    }

    // public
    public static void tampilkanInventaris() {
        if (getInventaris().isEmpty()) {
            pesanDataKosong();
            return;
        }

        System.out.println("\n========================================");
        System.out.println("  DAFTAR INVENTARIS SAMARINDA FREEDIVER");
        System.out.println("========================================");

        cetakHeader();

        for (AlatFreedive alat : getInventaris()) {
            String baris = "| " + formatKolom(alat.getIdAlat(), 8) +
                    " | " + formatKolom(alat.getNamaAlat(), 20) +
                    " | " + formatKolom(alat.getMerk(), 16) +
                    " | " + formatKolom(alat.getKondisi(), 13) +
                    " |";
            System.out.println(baris);
        }

        cetakGaris();
        System.out.println("Total alat tercatat: " + getTotalAlat() + " item");
    }

    // public
    public static void ubahKondisiAlat(Scanner input) {
        System.out.println("\n========================================");
        System.out.println("         UBAH KONDISI ALAT");
        System.out.println("========================================");

        if (getInventaris().isEmpty()) {
            pesanDataKosong();
            return;
        }

        tampilkanInventaris();

        System.out.print("\nMasukkan ID Alat yang ingin diubah kondisinya (contoh: 001): ");
        String idCari = input.nextLine();

        boolean ditemukan = false;

        for (int i = 0; i < getInventaris().size(); i++) {
            AlatFreedive alat = getInventaris().get(i);

            if (alat.getIdAlat().equals(idCari)) {
                ditemukan = true;
                System.out.println("[i] Alat ditemukan: " + alat.getNamaAlat() + " | Kondisi saat ini: " + alat.getKondisi());

                String kondisiBaru = pilihKondisi(input);
                alat.setKondisi(kondisiBaru);

                System.out.println("\n================================");
                System.out.println("  Kondisi alat berhasil diubah");
                System.out.println("================================");
            }
        }

        if (!ditemukan) {
            System.out.println("\n[!] Alat dengan ID '" + idCari + "' tidak ditemukan di inventaris");
        }
    }

    // public
    public static void hapusAlat(Scanner input) {
        System.out.println("\n========================================");
        System.out.println("            HAPUS ALAT");
        System.out.println("========================================");

        if (getInventaris().isEmpty()) {
            pesanDataKosong();
            return;
        }

        tampilkanInventaris();

        System.out.print("\nMasukkan ID Alat yang ingin dihapus (contoh: 001): ");
        String idHapus = input.nextLine();

        boolean ditemukan = false;

        for (int i = 0; i < getInventaris().size(); i++) {
            AlatFreedive alat = getInventaris().get(i);

            if (alat.getIdAlat().equals(idHapus)) {
                ditemukan = true;
                System.out.println("\n[i] Alat ditemukan: " + alat.getNamaAlat() + " (" + alat.getMerk() + ")");
                System.out.print("Apakah Anda yakin ingin menghapus alat ini? (y/n): ");
                String konfirmasi = input.nextLine();

                if (konfirmasi.equals("y") || konfirmasi.equals("Y")) {
                    getInventaris().remove(i);
                    AlatFreedive.renumberIds(getInventaris());
                    System.out.println("\n====================================================");
                    System.out.println("  Alat '" + alat.getNamaAlat() + "' berhasil dihapus dari inventaris");
                    System.out.println("====================================================");
                } else {
                    System.out.println("\n[i] Penghapusan dibatalkan.");
                }
                break;
            }
        }

        if (!ditemukan) {
            System.out.println("\n[!] Alat dengan ID '" + idHapus + "' tidak ditemukan di inventaris");
        }
    }

    // public
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        boolean programBerjalan = true;

        while (programBerjalan) {

            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║   SISTEM MANAJEMEN INVENTARIS            ║");
            System.out.println("║   KOMUNITAS SAMARINDA FREEDIVER          ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  1. Tambah Alat Baru                     ║");
            System.out.println("║  2. Tampilkan Inventaris                 ║");
            System.out.println("║  3. Ubah Kondisi Alat                    ║");
            System.out.println("║  4. Hapus Alat                           ║");
            System.out.println("║  5. Keluar                               ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("Masukkan pilihan menu (1-5): ");

            String pilihan = input.nextLine();

            switch (pilihan) {
                case "1":
                    tambahAlat(input);
                    break;
                case "2":
                    tampilkanInventaris();
                    break;
                case "3":
                    ubahKondisiAlat(input);
                    break;
                case "4":
                    hapusAlat(input);
                    break;
                case "5":
                    System.out.println("\n================================================================");
                    System.out.println("  Terima kasih! Program Inventaris Samarinda Freediver ditutup");
                    System.out.println("                DIVE SAFE, NEVER DIVE ALONE!");
                    System.out.println("================================================================");
                    programBerjalan = false;
                    break;
                default:
                    System.out.println("[!] Pilihan tidak valid. Masukkan angka 1 sampai 5");
            }
        }

        input.close();
    }
}