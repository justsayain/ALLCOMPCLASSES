public class test {

   public static void main(String[] args) {
      int i;
      System.out.println("Phase 1.");
      String182 string1 = new String182("comp");
      compare( string1, "comp" );
      String182 string3 = new String182(string1);
      compare( string3, "comp" );
      string1 = new String182("182");
      compare( string1, "182" );
      outStrInfo(string1, string3);
      String182 string2 = new String182("");
      compare( string2, "" );
      outStrInfo(string1, string2);
      string1.addHead('2');
      compare( string1, "2182" );
      string2.addHead('2');
      compare( string2, "2" );
      outStrInfo(string1, string2);
      
      System.out.println("Phase 2.");
      string2 = new String182("");
      compare( string2, "" );
      string1.addTail('4');
      compare( string1, "21824" );
      string2.addTail('4');
      compare( string2, "4" );
      outStrInfo(string1, string2);
      
      System.out.println("Phase 3.");
      string1 = new String182("abc");
      compare( string1, "abc" );
      string2 = new String182(string1);
      string1.addTail('*');
      compare( string1, "abc*" );
      outStrInfo(string1, string2);
      string2.addTail('$');
      compare( string1, "abc*" );
      compare( string2, "abc$" );
      outStrInfo(string1, string2);
      
      System.out.println("Phase 4.");
      string1 = new String182("aabbacbaacbcbaaccbbadbacabaa");
      string2 = new String182("dddaabbccbbccaaddbbaaddaaaabbb");
      string1.removeChar('d');
      string2.removeChar('d');
      compare( string1, "aabbacbaacbcbaaccbbabacabaa" );
      compare( string2, "aabbccbbccaabbaaaaaabbb" );
      outStrInfo(string1, string2);
      string1.removeChar('e');
      string2.removeChar('e');
      compare( string1, "aabbacbaacbcbaaccbbabacabaa" );
      compare( string2, "aabbccbbccaabbaaaaaabbb" );
      outStrInfo(string1, string2);
      string1.removeChar('a');
      string2.removeChar('a');
      compare( string1, "bbcbcbcbccbbbcb" );
      compare( string2, "bbccbbccbbbbb" );
      outStrInfo(string1, string2);
      string1.removeChar('c');
      string2.removeChar('c');
      compare( string1, "bbbbbbbbb" );
      compare( string2, "bbbbbbbbb" );
      outStrInfo(string1, string2);
      string1.removeChar('b');
      string2.removeChar('b');
      compare( string1, "" );
      compare( string2, "" );
      outStrInfo(string1, string2);
      string1.removeChar('b');
      string2.removeChar('b');
      compare( string1, "" );
      compare( string2, "" );
      outStrInfo(string1, string2);
      
      System.out.println("Phase 5.");
      string1 = new String182();
      string2 = new String182();
      string1.concatenate(string2);
      string2.concatenate(string2);
      compare( string1, "" );
      compare( string2, "" );
      outStrInfo(string1, string2);
      string1 = new String182("abc");
      string2 = new String182("12345");
      string1.concatenate(string2);
      string2.addTail('$');
      compare( string1, "abc12345" );
      compare( string2, "12345$" );
      outStrInfo(string1, string2);
      string1 = new String182("");
      string1.concatenate(string1);
      string2.concatenate(string1);
      compare( string2, "12345$" );
      string1.concatenate(string2);
      compare( string1, "12345$" );      

      System.out.println("Phase 6.");
      string1 = new String182("abc");
      string1.merge(string2);
      string1.merge(string2);
      compare( string1, "a112b324c53$45$" );
      compare( string2, "12345$" );
      outStrInfo(string1, string2);
      string2.merge(string2);
      compare( string1, "a112b324c53$45$" );
      compare( string2, "1122334455$$" );
      outStrInfo(string1, string2);
      string1.chars();
      string2.chars();
      compare( string1, "$12345abc" );
      compare( string2, "$12345" );
      outStrInfo(string1, string2);
      string1 = new String182("");
      string1.merge(string1);
      string2.merge(string1);
      compare( string2, "$12345" );
      string1.merge(string2);
      compare( string1, "$12345" );      
      
      System.out.println("Phase 7.");
      string1 = new String182("abcdef");
      string1.reverse();
      string2 = new String182(string1);
      string2.merge(string2);
      string2.reverse();
      compare( string1, "fedcba" );
      compare( string2, "aabbccddeeff" );
      outStrInfo(string1, string2);
      string2 = new String182("");
      string2.reverse();
      compare( string2, "" );
}
   
   private static void outStrInfo(String182 str1, String182 str2) {
      System.out.print("str1: " + str1 + ", len = " + str1.length());
      System.out.println("  str2: " + str2 + ", len = " + str2.length());
   }
   
   private static void compare(String182 str1, String str2) {
       if (str1.toString().compareTo(str2) != 0)
           System.out.println("*******   " + str1 + " different from " + str2 + " ******");
   }
}

/*  My output

Phase 1.
str1: 182, len = 3  str2: comp, len = 4
str1: 182, len = 3  str2: , len = 0
str1: 2182, len = 4  str2: 2, len = 1
Phase 2.
str1: 21824, len = 5  str2: 4, len = 1
Phase 3.
str1: abc*, len = 4  str2: abc, len = 3
str1: abc*, len = 4  str2: abc$, len = 4
Phase 4.
str1: aabbacbaacbcbaaccbbabacabaa, len = 27  str2: aabbccbbccaabbaaaaaabbb, len = 23
str1: aabbacbaacbcbaaccbbabacabaa, len = 27  str2: aabbccbbccaabbaaaaaabbb, len = 23
str1: bbcbcbcbccbbbcb, len = 15  str2: bbccbbccbbbbb, len = 13
str1: bbbbbbbbb, len = 9  str2: bbbbbbbbb, len = 9
str1: , len = 0  str2: , len = 0
str1: , len = 0  str2: , len = 0
Phase 5.
str1: , len = 0  str2: , len = 0
str1: abc12345, len = 8  str2: 12345$, len = 6
Phase 6.
str1: a112b324c53$45$, len = 15  str2: 12345$, len = 6
str1: a112b324c53$45$, len = 15  str2: 1122334455$$, len = 12
str1: $12345abc, len = 9  str2: $12345, len = 6
Phase 7.
str1: fedcba, len = 6  str2: aabbccddeeff, len = 12

*/