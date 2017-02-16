public class test2 {

   public static void main(String[] args) {
     String182 s1= new String182("catatonic");
     System.out.println(s1);
     String182 a = new String182("a");
     s1.remove(a);
     System.out.println(s1);
     System.out.println(s1.myName());
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

*/
