// Логика1.3.cpp : Этот файл содержит функцию "main". Здесь начинается и заканчивается выполнение программы.
#include <stdio.h>
#include <malloc.h>
#include <stdlib.h>
#include <locale.h>
#include <time.h>

int main()
{
    setlocale(LC_ALL, "Rus");
    int** a1;  // указатель на массив
    int i, j, n, m;
    srand(time(NULL));

    printf("    Введите размер массива\n");
    printf("Число строк:    ");
    scanf("%d", &n);
    printf("Число столбцов: ");
    scanf("%d", &m);
    a1 = (int**)malloc(m * sizeof(int*));
    for (i = 0; i < n; i++)
    {
        a1[i] = (int*)malloc(n * sizeof(int));
        for (j = 0; j < m; j++)
        {
            *(a1 + i * m + j) = rand() % (100 + 100 + 1) - 100;
        }
    }
    printf("\n  Массив:\n");
    for (i = 0; i < n; i++)
    {
        for (j = 0; j < m; j++)
        {
            printf("[%d][%d]=%3d ", i, j, *(a1 + i * m + j));
        }
        printf("\n");
    }
    free(a1);
    getchar();
    getchar();
    return 0;
}