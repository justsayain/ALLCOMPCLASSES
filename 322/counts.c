#include <stdio.h>

main(){
   int blank_cnt=0,c,digit_cnt=0, letter_cnt=0,nl_cnt=0,other=0;
   while((c=getchar()) !=EOF)
      if(c==' ')
         ++blank_cnt;
      else if(c>='0'&&c<='9')
         ++digit_cnt;
      else if((c>='a'&&c<='z')||(c>='A'&&c<='Z'))
         ++letter_cnt;
      else if(c== '\n')
         ++nl_cnt;
      else
         ++other;
         
      printf("%10s%10s%10s%10s%10s%10s \n\n",
         "blanks","digits","letters","lines","others","total");
      printf("%10d%10d%10d%10d%10d%10d \n\n",
         blank_cnt,digit_cnt,letter_cnt,nl_cnt,other,
            blank_cnt+digit_cnt+letter_cnt+nl_cnt+other);
}