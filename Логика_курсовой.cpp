#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <locale.h>
#include <queue>
#include <stdio.h>
#include <time.h>
#include <locale>
#include <conio.h> 
#include <malloc.h>
using namespace std;

int* step;
int num, num2;
int** m;
int menu;

int main() {
	setlocale(LC_ALL, "Rus");
	printf("\tАлгоритм нахождения изолированных вершин в графе\n\n");
	printf("Выберите действие:\n");
	printf("	Ввод массива вручную - 1\n	Случайно сгенерированный массив - 2\n");
	
	
	printf("\nВаше действие: ");
	scanf("%d", &menu);
	printf("	Введите размер массива: ");
	scanf("%d", &num);
	num2 = num * num;
	
	m = (int**)malloc(num * sizeof(int*));
	for (int i = 0; i < num; i++) {
		m[i] = (int*)malloc(num * sizeof(int));
	}
	float n;
	float prob;

	if (menu == 2) {
		printf("	Введите вероятность появление 0: ");
		scanf("%f", &prob);
		for (int i = 0; i < num; i++) {
			for (int j = 0; j <= i; j++) {
				m[i][j] = rand() % 2;
				n = ((float)rand()) / RAND_MAX;
				if (n < prob) {
					m[i][j] = 0;
				}
				m[j][i] = m[i][j];
				m[i][i] = 0;
			}
		}
	}

	else {
		printf("\nВведите массив из %d чисел:\n", num2);
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				scanf("%d", &m[i][j]);
			}
		}
	}

	printf("\n");
	printf("Массив:\n");
	printf("   ");
	for (int i = 0; i < num; i++)
		printf(" v%d", i);
		printf("\n");

	for (int i = 0; i < num; i++) {
		printf(" v%d", i);
		for (int j = 0; j < num; j++) {
			printf("%3d", m[i][j]);
		}
		printf("\n");
	}

	int iso = 0;
	printf("Изолированные вершины: ");
	for (int i = 0; i < num; i++) {
		int step = 0;
		for (int j = 0; j < num; j++) {
			if (m[i][j] == 1) step++;
		}
		if (step == 0) {
			printf("%d ", i);
			iso++;
		}
	}
	
	printf("\n");
	printf("Количество изолированных вершин: %d", iso);
	for (int i = 0; i < num; i++)
		free(m[i]);
	free(m);
	getchar();   
	getchar();
}