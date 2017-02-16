

public class test3 {

    public static void main(String[] args) {
            int ran_num = 123, i, j;
        String182 s;
        for (i=10; i >= 0; i--) {
            ran_num = (ran_num * 101 + 103) % 1000003;
            s = new String182 (new String(new char[i]).replace('\0', ' ') );
            for (j = 0; j < i; j++ ) {
                s.setElement((char) (ran_num % 74 + 48), j);
                ran_num = (ran_num * 101 + 103) % 1000003;
            }
            System.out.print(i + ":  out: ");
            s.out();
            System.out.print("  ");
            System.out.print("outm: ");
            s.outm();
            System.out.print("  ");
            if (s.biggest() < 0)
                System.out.print("   empty string.");
             else
                System.out.print("   biggest: " + s.biggest());

            System.out.print("  sort: ");
            //s.insertionSort();
            //s.out();
            System.out.println();
            System.out.print("   rev: ");
            s.outreverse();
            s.reverse();
            System.out.print("   reved: ");
            s.outm();
            System.out.println();
         }
        for (i=1; i <= 15; i++) {
            ran_num = (ran_num * 101 + 103) % 1000003;
            s = new String182 (new String(new char[15]).replace('\0', ' ') );
            for (j = 0; j < 15; j++ ) {
                s.setElement((char) (ran_num % 26 + 97), j);
                ran_num = (ran_num * 101 + 103) % 1000003;
            }
            System.out.print(i + ":  out: ");
            s.out();
            System.out.print("  ");
            System.out.print("outm: ");
            s.outm();
            System.out.print("  ");
            //if (s.biggest() < 0)
            //    System.out.print("   empty string.");
            // else
            //    System.out.print("   biggest: " + s.biggest());
            System.out.print("  sort: ");
            //s.insertionSort();
            //s.out();
            System.out.println();
            System.out.print("   rev: ");
            s.outreverse();
            s.reverse();
            System.out.print("   reved: ");
            s.outm();
            System.out.println();
         }
    }
}

/*   My Output

10:  out: D:_i]<;@kK  outm: D:_i]<;@kK     biggest: 8  sort: :;<@DK]_ik
rev: ki_]KD@<;:   reved: ki_]KD@<;:
9:  out: 2tPwH\K:3  outm: 2tPwH\K:3     biggest: 3  sort: 23:HKP\tw
rev: wt\PKH:32   reved: wt\PKH:32
8:  out: K7^i5b`d  outm: K7^i5b`d     biggest: 3  sort: 57K^`bdi
rev: idb`^K75   reved: idb`^K75
7:  out: Sg41Gln  outm: Sg41Gln     biggest: 6  sort: 14GSgln
rev: nlgSG41   reved: nlgSG41
6:  out: :FXm^j  outm: :FXm^j     biggest: 3  sort: :FX^jm
rev: mj^XF:   reved: mj^XF:
5:  out: \OPHh  outm: \OPHh     biggest: 4  sort: HOP\h
rev: h\POH   reved: h\POH
4:  out: vN\\  outm: vN\\     biggest: 0  sort: N\\v
rev: v\\N   reved: v\\N
3:  out: Coe  outm: Coe     biggest: 1  sort: Ceo
rev: oeC   reved: oeC
2:  out: Mr  outm: Mr     biggest: 1  sort: Mr
rev: rM   reved: rM
1:  out: V  outm: V     biggest: 0  sort: V
rev: V   reved: V
0:  out:   outm:      empty string.  sort:
rev:    reved:
1:  out: zpcydmpnficiuyx  outm: zpcydmpnficiuyx     biggest: 0  sort: ccdfiimnppuxyyz
rev: zyyxuppnmiifdcc   reved: zyyxuppnmiifdcc
2:  out: muvpdttvvydrdxt  outm: muvpdttvvydrdxt     biggest: 9  sort: dddmprtttuvvvxy
rev: yxvvvutttrpmddd   reved: yxvvvutttrpmddd
3:  out: kdykwgmbizhjjnv  outm: kdykwgmbizhjjnv     biggest: 9  sort: bdghijjkkmnvwyz
rev: zywvnmkkjjihgdb   reved: zywvnmkkjjihgdb
4:  out: vjdtfxrzxwacxvr  outm: vjdtfxrzxwacxvr     biggest: 7  sort: acdfjrrtvvwxxxz
rev: zxxxwvvtrrjfdca   reved: zxxxwvvtrrjfdca
5:  out: joqgnqqsbfvwlwq  outm: joqgnqqsbfvwlwq     biggest: 11  sort: bfgjlnoqqqqsvww
rev: wwvsqqqqonljgfb   reved: wwvsqqqqonljgfb
6:  out: iitktirqrnjydyi  outm: iitktirqrnjydyi     biggest: 11  sort: diiiijknqrrttyy
rev: yyttrrqnkjiiiid   reved: yyttrrqnkjiiiid
7:  out: wbienebtmwlexci  outm: wbienebtmwlexci     biggest: 12  sort: bbceeeiilmntwwx
rev: xwwtnmliieeecbb   reved: xwwtnmliieeecbb
8:  out: vsujbyshpperbxz  outm: vsujbyshpperbxz     biggest: 14  sort: bbehjpprssuvxyz
rev: zyxvussrppjhebb   reved: zyxvussrppjhebb
9:  out: trazzsirbhdwssb  outm: trazzsirbhdwssb     biggest: 3  sort: abbdhirrssstwzz
rev: zzwtsssrrihdbba   reved: zzwtsssrrihdbba
10:  out: zulcnmdsojugtse  outm: zulcnmdsojugtse     biggest: 0  sort: cdegjlmnosstuuz
rev: zuutssonmljgedc   reved: zuutssonmljgedc
11:  out: llvjeowzrllsbxo  outm: llvjeowzrllsbxo     biggest: 7  sort: bejlllloorsvwxz
rev: zxwvsroolllljeb   reved: zxwvsroolllljeb
12:  out: eqovrehuxwcrtvq  outm: eqovrehuxwcrtvq     biggest: 8  sort: ceehoqqrrtuvvwx
rev: xwvvutrrqqoheec   reved: xwvvutrrqqoheec
13:  out: qawspahjqileqxx  outm: qawspahjqileqxx     biggest: 13  sort: aaehijlpqqqswxx
rev: xxwsqqqpljiheaa   reved: xxwsqqqpljiheaa
14:  out: biydjxdsbntidrk  outm: biydjxdsbntidrk     biggest: 2  sort: bbdddiijknrstxy
rev: yxtsrnkjiidddbb   reved: yxtsrnkjiidddbb
15:  out: ykdcvjfrsmbkfto  outm: ykdcvjfrsmbkfto     biggest: 0  sort: bcdffjkkmorstvy
rev: yvtsromkkjffdcb   reved: yvtsromkkjffdcb

*/
