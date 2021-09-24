// Логика1.4.cpp : Этот файл содержит функцию "main". Здесь начинается и заканчивается выполнение программы.
//

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <locale.h>

int main() {
    setlocale(LC_ALL, "Rus");
    int i, j;
    int ABC[4][6]; // объявляем массив размером 4 на 6 элементов
    int y;
    srand(time(NULL));

    for (i = 0; i < 4; i++) {
        for (j = 0; j < 6; j++)
            ABC[i][j] = (-10 + rand() % 40);
    }

    printf("Массив:\n");
    for (i = 0; i < 4; i++) {
        for (j = 0; j < 6; j++) {
            printf("[%d][%d]=%3d ", i, j, ABC[i][j]);
        }
        printf("\n");
    }

    printf("\nСумма элементов:");
    for (j = 0; j < 6; j++) {
        y = 0;
        for (i = 0; i < 4; i++) {
                y += ABC[i][j];
        }
        printf("\n  в столбце № %d: %d", j, y);
    }

}
