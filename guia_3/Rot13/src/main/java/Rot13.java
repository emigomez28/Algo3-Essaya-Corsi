public class Rot13 {
    public String cifrar(String cadena) {
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            c += 13;
            if (c > 'z') {
                c -= 26;
            }
            r.append(c);
        }
        return r.toString();
    }
}
