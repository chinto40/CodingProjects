#include "sys/types.h"
#include "string.h"
#include "stdlib.h"
#include "stdio.h"
#include "unistd.h"
#include "pthread.h"
#include "semaphore.h"

/*
 * Main Struct for this program.
 */
struct word_count{
    char* word;
    int count;
    struct word_count* Next;
};

/*
 * struct for threads info. 
 */
struct td{
    char* fileName;
    int id;
    struct td* next;
};

// Global Variables
struct td* tdHead = NULL;
struct word_count* Head = NULL;
struct word_count* Tail = NULL;
int counter =0;
sem_t * sem;
int wordCount = 0;
int wordFile = 0;
int totalCount = 0;
int overallCount = 0;

//Declaration
void freeMyList();
void printList(int);
int myStringCompare(char*, char*);
int changeCase(int);
void checkInsert(int,struct word_count*, int);
void myStringCopy(char*, char*, int);
void insertNode(char*);
void myLogic(char*);
char* getWord(char*, int);
int countWord(char*);
char* ReadLineFile(FILE*);
void forkRec(int, char**, int, int);
void freeList();

/*
 * Frees and deallocates my Dynamically allocated Linked List.
 */
void freeMyList(){
    struct word_count* current = Head;
    struct word_count* next = Head->Next;
    if(current != NULL){
        return ;
    }
    while(1){
        free(current->word);
        free(current);
        current= next;
        next = next->Next;
        if(current == NULL){
            break;
        }
    }
}

/*
 * Prints out the Contents of the Linked List.
 * pid -  The current Process ID printing out the List. 
 */
void printList(int argc){
    struct word_count *current = Head;
    printf("----------------------------------------------------\n");    
    printf("All %d files have been counted and the total of %d new words, Total words %d\n", argc, totalCount,overallCount);
    if(Head == NULL){
        printf("Error: List is empty...\n");
        exit(0);
        return;
    }else{
        while(1){
            if(current == NULL){
                break;
            }
            printf("%s: appears %d Times\n", current->word, current->count);
            current = current->Next;
        }
    }
}

/* Creating my own strcmp function that I can Manipulate, because why not.... 
 * line1 - the first string being compared.
 * line2 - the second string being compared.
 */
int myStringCompare(char* line1, char* line2){
    // returns a 1 for line 1 is value is higher in alphabet order..
    // returns a 2 for line 2 is value is hight in alphabet order...
    // returns a 0 for line 1 and 2 are equal...
    // 65- 90 caps letter.... 97 - 122 lowercase letters...
    int index =0;
    int ascVal1 = 0;
    int ascVal2 = 0;
    int returnIndex = 0;
    while((*(line1 + index) != '\0' && (*(line2 +index)) != '\0')){ // have to check both strings for '\0' 
        int ascVal1 = (int) (*(line1+index));
        int ascVal2 = (int) (*(line2+index));
        ascVal1 = changeCase(ascVal1);
        ascVal2 = changeCase(ascVal2);
       
        if(ascVal1 > ascVal2){ // line2 is on top..
            returnIndex = 1;
            return 2;
        }else if(ascVal1 < ascVal2){ // line1 is on top... 
            returnIndex = 2;
            return 1;
        }
        index++;
    }
    if(returnIndex == 0){
        if(strlen(line1) < strlen(line2)){
            return 1;
        }else if(strlen(line1) > strlen(line2)){
            return 2;
        }else{
            return 0;
        }
    }
    return returnIndex;
}

/*
 * Changes the case of my character.
 * num - the num representation of my character.
 */
int changeCase(int num){
    
    if(num > 96 && num < 123){
        return (num-32);
    }
    return num;
}

/*
 * Inserts the Node either at the Head,Body, or the Tail of the linked list.
 * iter - The number of iteration of the linked list.
 * node - The Current word_count struct being passed in.
 * checkEnter - Decides where we insert the node. 
 */
void checkInsert(int iter,struct word_count * node,int checkEnter){
    struct word_count* c = Head;
    int i;
    for(i = 0; i < iter; i++){
        c = c->Next;
    }
    switch(checkEnter){
        case 1: // Head
            node->Next = Head;
            Head = node;
            break;
        case 2: // body
            node->Next = c->Next;
            c->Next = node;
            break;
        case 3: // Tail
           c->Next = node;
           break;
    }
}

/*
 * Made my own String copy function to manipulate, Because why not...
 * newCopy - The new string being created.
 * oldCopy - The old string being copied.
 * length - The length of the old String being copied. 
 */
void myStringCopy(char* newCopy, char* oldCopy, int length){
    int i = 0;
    newCopy = (char*) realloc(newCopy,length); // do a realloc
    for(i =0;i< length; i++){
       *(newCopy+i) = *(oldCopy+i);
    }
    *(newCopy+length) = '\0';
}

/*
 * The logic to decide where the Insertion of the node is going to happen or incremented.
 * word - The Word being inserted or incremented.
 */
void insertNode(char* word){
    struct word_count* current = Head;
    struct word_count* before = NULL;
    struct word_count* checkCurrent = NULL;
    int check = 0;
    int isFirstTime = 0;
    int iterations = 0;
    wordFile++;
    overallCount++;
    while(1){
        if(current == NULL){
            break;
        }
        if((myStringCompare(current->word,word)) == 0){ // the equal
            current->count++;
            return;
        } 
        if((myStringCompare(current->word, word)) == 2){
           if(iterations > 0){
               check = 3;
           }else{
                check = 0;
           }
           break;
        }
        current = current->Next;
        if(current == NULL){
            check = 1;
        }
       iterations++;
    }
    
    struct word_count* node = (struct word_count*) malloc(sizeof(struct word_count));
    node->word = (char*) malloc(1*sizeof(char));
    if(node == NULL){
        printf("Not enough memory for a node Creation\n");
        exit(0);
    }  
    node->count= 1; 
    myStringCopy(node->word,word,strlen(word));
    node->Next = NULL;

     if(check == 0 || iterations == 0){ //Head
        checkInsert(iterations,node,1);
    }else if(check == 3){ // Body
       checkInsert((iterations-1),node,2);
    }else if(check == 1){ // Tail
        checkInsert((iterations-1),node,3);
    }
    wordCount++;
    totalCount++;
}
  
/*
 * Takes apart my line and returns each word for processing.
 * line - The line of words being ecaluated.
 */
void myLogic(char* line){    
    int wordCountInLine = countWord(line);
    int i;
    for(i=0;i< wordCountInLine; i++){
        char* readWord = getWord(line,i);
        insertNode(readWord);
        free(readWord);
    }    
    
}

/*
 * Gets a specific word from a line of words. 
 * line - The line of words being evaluated.
 */
char* getWord(char* line,int wordCount){ 
    char* word = (char*) malloc(1* sizeof(char)); //dynamically create a word here...
    int indexLine = 0;
    int isCount = 0;
    char* start = NULL;
    int i = 0;
    int wordLength = 0;
    int whatWord = wordCount;
    int isSpace = 0;
        while(*(line + indexLine) != '\0' && *(line + indexLine) != EOF){
            int ascVal = (int) *(line+indexLine);
            if(ascVal == 32 || (char) *(line +indexLine) == '\n'){
                if(isSpace == 0){
                    isCount = 0; //space 
                    if(whatWord == 0){
                        int i = 0;
                        // copy word...   
                        myStringCopy(word,start,wordLength);
                        return &(*(word));
                    }
                    wordLength = 0;
                    whatWord--;
                    isSpace = 1;
               }
            }else{
                if(isCount == 0){
                    start = (line + indexLine); // START OF WORD
                }
                wordLength++;
                isCount = 1;
                isSpace = 0;
            }
            indexLine++;
        }
        if(whatWord == 0){
            int i = 0;
            myStringCopy(word,start,wordLength);
        }   
    return &(*(word));
}



/*
 * Reads and counts whitespaces for proper counting on how many words exist
 * within the lin of a file.
 * line - the line being counted.
 */
 int countWord(char* line){
    int indexLine = 0;
    int wordCount = 0;
    int isCount = 0;
    char* word = NULL;
    while(*(line + indexLine) != '\0' && *(line + indexLine) != EOF){
        int ascVal = (int) *(line+indexLine);
        if(ascVal == 32 || (char) *(line+indexLine) == '\n'){ // not a space or new line.
            isCount = 0;
        }else{
            if(isCount == 0){
                wordCount++;
                isCount = 1;
            }
        }
        indexLine++;
    }
    return wordCount;
 }


 /*
  * Reads a line from a file to be dismantled for the sport of word Counting.
  * infile - the file * pointing at a file being read in.
  */
char* ReadLineFile(FILE *infile){
    int maxBufferSize = 256;
    char* buffer = malloc(sizeof(char) * maxBufferSize);
    if(buffer != NULL){
        int sizeCounter = 0;
        int input = EOF;
        while((input = getc(infile)) != EOF && input != '\n'){
            *(buffer + sizeCounter) = (char) input;
            sizeCounter++;
            if(sizeCounter >= maxBufferSize){
                maxBufferSize += maxBufferSize;
                buffer = realloc(buffer, maxBufferSize);
            }
        }
        if(sizeCounter != 0){
            buffer = realloc(buffer, sizeCounter);
        }
        *(buffer + sizeCounter) = '\0';
    }else{
        printf("Not enough Memory!!!\n");
    }
    return &(*buffer);
}



/*
 * Function used by the thread that will take in the file open it, count the words, and add it to the gloabal array.
 */
void* threadLine(void* arg){
    struct td* curr = (struct td*) arg;
    char* file =(char*)curr->fileName;
    int id = curr->id;
    sem_wait(&sem[id]);
    FILE* fd = fopen(file,"r");
    if(fd == NULL){
        printf("Erro: File doesnt't exsist\n");
        exit(0);
    }
    while(!(feof(fd))){
        char* line = ReadLineFile(fd);
        myLogic(line);
        free(&(*line));
    }
    // Might have to pass the number here.  make an int [] for id threads. 
    printf("Thread %d: number of new words in %s is %d, Total Words %d\n", id,file,wordCount,wordFile);
    wordCount = 0;
    wordFile = 0;
    fclose(fd);
    sem_post(&sem[id+1]);
    pthread_exit(NULL);
}


/*
 * Insert a Node to the struct td type linklist used to hold the file name and thread id.
 */
void insert(struct td* node){
    
    if(tdHead == NULL){
        tdHead = node;
   
    }else{
        // iterate
        struct td* iter = tdHead;
        while(iter->next != NULL){
            iter = iter->next;
        }
        iter->next = node;
    }
    
}

/*
 * Frees the struct td type link list from memory.
 */
void freeList(){
    struct td* current = tdHead;
    struct td* nex = tdHead->next;
    if(current != NULL){
        return;
    }
    while(1){
        free(current->fileName);
        free(current);
        current = nex;
        nex = nex->next;
        if(current == NULL){
            break;
        }
    
    }
    
}

/*
 * The main application of this Program. 
 */
int main(int argc, char* argv[]){
    if(argc > 1){ // arguments.
        int i = 0;
        int mainSize = (argc -1);
        sem_t s[mainSize];
        sem = s;
        // create the threads
        pthread_t td[mainSize];
        
        for(i = 0; i <(mainSize); i++){
            sem_init(&sem[i],0,0);
           struct td* current = (struct td*)malloc(sizeof(struct td));
                current->id = i;
                current->fileName = (char*) malloc(sizeof(char) * sizeof(argv[(1+i)]));
                current->fileName = (argv[(1+i)]);
               // printf("Creating thread...\n");
            pthread_create(&td[i], NULL, threadLine, (void*)current);
                insert(current);
                
            //hold a link list of this and free it at the end.. 
            //free(current->fileName);
            //free(current);
        }
        sem_post(&sem[0]);
        for(i = 0; i < (mainSize); i++){
            pthread_join(td[i], NULL);
            sem_destroy(&sem[i]);
        }
        
        printList(mainSize);

        freeMyList();
        freeList(); 
    }else{
        printf(" >:|, Not Enough Arguments\n");
    }
    return 0;
}
