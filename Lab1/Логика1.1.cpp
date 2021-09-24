// Логика1.1.cpp : Этот файл содержит функцию "main". Здесь начинается и заканчивается выполнение программы.
//

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <locale.h>

int main() {
	setlocale(LC_ALL, "Rus");
	int M[10]; //Создание массива
	srand(time(NULL));
	int m1 = 0;
	int m2 = 100;

	printf(" Массив:\n");

	for (int i = 0; i < 10; i++) { //Заполнение массива
		M[i] = rand() % (100 + 100 + 1) - 100;
		printf("	[%d]=%d  \n", i, M[i]);
	}

	for (int i = 0; i < 10; i++) {
		if (M[i] < m2) {
			m2 = M[i];
		}
		if (M[i] > m1) {
			m1 = M[i];
		}
	}
	printf("\nНаибольший: %i\nНаименьший: %i", m1, m2);
	printf("\nРазница между ними: %i", m1 - m2);
		
}