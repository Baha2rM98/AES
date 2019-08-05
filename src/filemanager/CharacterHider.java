package filemanager;

abstract class CharacterHider {
    private static final byte MUL = -1;
    private static final byte PLUS = 51;

    static byte[] show(byte[] bytes) {
        final int size = bytes.length;
        byte[] buf = new byte[size];
        for (int i = 0; i < size; i++) {
            buf[i] = (byte) ((bytes[i] * MUL) + PLUS);
        }
        return buf;
    }
}
