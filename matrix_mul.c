#include <stdio.h>
#include <pthread.h>

#define SIZE 3

int A[SIZE][SIZE] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
int B[SIZE][SIZE] = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};
int C[SIZE][SIZE];

void* multiply_row(void* arg) {
    int row = *(int*)arg;
    for (int i = 0; i < SIZE; i++) {
        C[row][i] = 0;
        for (int j = 0; j < SIZE; j++) {
            C[row][i] += A[row][j] * B[j][i];
        }
    }
    return NULL;
}

int main() {
    pthread_t threads[SIZE];
    int row_indices[SIZE];

    for (int i = 0; i < SIZE; i++) {
        row_indices[i] = i;
        pthread_create(&threads[i], NULL, multiply_row, &row_indices[i]);
    }

    for (int i = 0; i < SIZE; i++) {
        pthread_join(threads[i], NULL);
    }

    printf("Result matrix:\n");
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            printf("%d ", C[i][j]);
        }
        printf("\n");
    }

    return 0;
}
