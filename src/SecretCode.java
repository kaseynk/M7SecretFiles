import java.util.HashMap;

public class SecretCode {
    public static Integer[] encode(String Message){
        Message= Message.toUpperCase();
        int messageLength= Message.length();
        Integer[] messageCode=new Integer[messageLength];

        for (int i=0;i<messageLength;i++){
            char c= Message.charAt(i);
            Integer Charcode=LETTER_ENCODE_MAP.get(c);
            if (Charcode==null || !LETTER_ENCODE_MAP.containsKey(c)){
                Charcode=27;
            }
            messageCode[i]=Charcode;

        }
        return messageCode;
    }

    public static String decode(Integer[] EncodedMessage){
        StringBuilder messageBuilder= new StringBuilder();
        for (Integer Charcode: EncodedMessage){
            Character c= INTEGER_DECODE_MAP.get(Charcode);
            if (c==null || ! INTEGER_DECODE_MAP.containsKey(Charcode)){
                c=' ';
            }
            messageBuilder.append(c);

        }
        return messageBuilder.toString();

    }

    public static final HashMap<Character,Integer> LETTER_ENCODE_MAP=new HashMap<>();
    static {

        LETTER_ENCODE_MAP.put('A',1);
        LETTER_ENCODE_MAP.put('B',2);
        LETTER_ENCODE_MAP.put('C',3);
        LETTER_ENCODE_MAP.put('D',4);
        LETTER_ENCODE_MAP.put('E',5);
        LETTER_ENCODE_MAP.put('F',6);
        LETTER_ENCODE_MAP.put('G',7);
        LETTER_ENCODE_MAP.put('H',8);
        LETTER_ENCODE_MAP.put('I',9);
        LETTER_ENCODE_MAP.put('J',10);
        LETTER_ENCODE_MAP.put('K',11);
        LETTER_ENCODE_MAP.put('L',12);
        LETTER_ENCODE_MAP.put('M',13);
        LETTER_ENCODE_MAP.put('N',14);
        LETTER_ENCODE_MAP.put('O',15);
        LETTER_ENCODE_MAP.put('P',16);
        LETTER_ENCODE_MAP.put('Q',17);
        LETTER_ENCODE_MAP.put('R',18);
        LETTER_ENCODE_MAP.put('S',19);
        LETTER_ENCODE_MAP.put('T',20);
        LETTER_ENCODE_MAP.put('U',21);
        LETTER_ENCODE_MAP.put('V',22);
        LETTER_ENCODE_MAP.put('W',23);
        LETTER_ENCODE_MAP.put('X',24);
        LETTER_ENCODE_MAP.put('Y',25);
        LETTER_ENCODE_MAP.put('Z',26);
        LETTER_ENCODE_MAP.put(' ',27);
        LETTER_ENCODE_MAP.put('.',28);



    }

    public static final HashMap<Integer, Character> INTEGER_DECODE_MAP = new HashMap<>();
    static
    {
        INTEGER_DECODE_MAP.put(1, 'A');
        INTEGER_DECODE_MAP.put(2, 'B');
        INTEGER_DECODE_MAP.put(3, 'C');
        INTEGER_DECODE_MAP.put(4, 'D');
        INTEGER_DECODE_MAP.put(5, 'E');
        INTEGER_DECODE_MAP.put(6, 'F');
        INTEGER_DECODE_MAP.put(7, 'G');
        INTEGER_DECODE_MAP.put(8, 'H');
        INTEGER_DECODE_MAP.put(9, 'I');
        INTEGER_DECODE_MAP.put(10, 'J');
        INTEGER_DECODE_MAP.put(11, 'K');
        INTEGER_DECODE_MAP.put(12, 'L');
        INTEGER_DECODE_MAP.put(13, 'M');
        INTEGER_DECODE_MAP.put(14, 'N');
        INTEGER_DECODE_MAP.put(15, 'O');
        INTEGER_DECODE_MAP.put(16, 'P');
        INTEGER_DECODE_MAP.put(17, 'Q');
        INTEGER_DECODE_MAP.put(18, 'R');
        INTEGER_DECODE_MAP.put(19, 'S');
        INTEGER_DECODE_MAP.put(20, 'T');
        INTEGER_DECODE_MAP.put(21, 'U');
        INTEGER_DECODE_MAP.put(22, 'V');
        INTEGER_DECODE_MAP.put(23, 'W');
        INTEGER_DECODE_MAP.put(24, 'X');
        INTEGER_DECODE_MAP.put(25, 'Y');
        INTEGER_DECODE_MAP.put(26, 'Z');
        INTEGER_DECODE_MAP.put(27, ' ');
        INTEGER_DECODE_MAP.put(28, '.');
    }
}
