/*  Steve Delagdo
Project 4 Virtual Address Mapping
3/4/14
*/
#include <stdio.h>
#include <stdlib.h>

/* declare global var's */
int MMsize, psize,replacement,entries,Paddr,j;
/* Define page table as dynamic structure containing virtual page and page frame
and initialize variable as pointer to structure */
struct node{
  int vp;
  int pf;
}  *pt=NULL;
typedef struct node row;

/**********************************************************************/
void option1()
{
  /* declare local var's */
  int i;
  /* Prompt for main memory size, page size, and replacement policy */
  printf("Enter Main Memory size: ");
  scanf("%d",&MMsize);
  printf("Enter page size: ");
  scanf("%d",&psize);
  printf("Enter replacement policy: ");
  scanf("%d",&replacement);
  /* Allocate and initialize page table based on number of entries */
  entries=MMsize/psize;
  pt= (row*)malloc(entries*sizeof(row));
  for(i=0;i<entries;i++){
    pt[i].vp=-1;
  }

  return;
}


/**********************************************************************/
void option2()
{
  /* declare local var's */
  int VMaddr,vpage,offset,i,a,Paddr,B=pt[0].pf;
  /* Prompt for virtual address */

  printf("Enter virtual memory address to access: ");
  scanf("%d",&VMaddr);

  /* Translate virtual mem addr to virtual page and offset*/
  vpage=VMaddr/psize;
  offset=VMaddr%psize;
  j=0;
  /* Check for end of table, unallocated entry, or matched entry in table
  and update table appropriately; while none of three cases, keep looping */
  while((j<entries)&&(pt[j].vp!=-1)&&(pt[j].vp!=vpage)){
    j++;
  }
  if(j==entries){
    a=pt[0].pf;
    for(i=0;i<=entries-2;i++){
      pt[i]=pt[i+1];
    }
    pt[entries-1].vp=vpage;
    pt[entries-1].pf=a;
    printf("fault!\n");
  }
  else if(pt[j].vp==-1){
    pt[j].vp=vpage;
    pt[j].pf=j;
    printf("fault\n");
  }
  else{/* In case of hit in page table, calculate physical address and print message */
    B=pt[j].pf;
    if(replacement==0){

      //printf("%d and %d and %d",B,offset,vpage);
      for(i=j;i<entries-1&&pt[i].vp!=-1;i++)
      pt[i]=pt[i+1];
    }
    pt[i].vp=vpage;
    pt[i].pf=B;
    Paddr=(B*psize)+offset;
    printf("physical addrs %d\n",Paddr);
  }

  return;
}
void option3(){
  /* declare local variables */
  int i;

  /* Print out each valid virtual page and page frame pair in table */
  for(i=0;i<=entries-1&&pt[i].vp!=-1;i++){
    //if(pt[i].vp==-1)
    // break;
    printf("VP %d --> PF %d \n",pt[i].vp, pt[i].pf);

  }
}


/**********************************************************************/
int main()
{
  /* declare local var's */
  int U=0;
  while(U!=4){
    printf("Virtual address mapping");
    printf("------------------------\n");
    printf("1) Enter Parameters\n");
    printf("2) Map Virtual address \n");
    printf("3) Print page table\n");
    printf("4) QUIT \n");
    printf("Enter Selection: ");
    scanf("%d",&U);
    switch(U){
      case 1:option1();
      break;
      case 2:option2();
      break;
      case 3:option3();
      break;
      case 4:break;

      default: printf("invalid input please input another number\n\n");
      break;
    }
  }

  /* until program exits, print menu, select choice via switch statement and call appropriate function*/
  return 1;
}
