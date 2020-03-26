/************************************************************************
Lab 9 Nios Software

Dong Kai Wang, Fall 2017
Christine Chen, Fall 2013

For use with ECE 385 Experiment 9
University of Illinois ECE Department
************************************************************************/

#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include "aes.h"

// Pointer to base address of AES module, make sure it matches Qsys
volatile unsigned int * AES_PTR = (unsigned int *) 0x000000040;

// Execution mode: 0 for testing, 1 for benchmarking
int run_mode = 0;

/** charToHex
 *  Convert a single character to the 4-bit value it represents.
 *
 *  Input: a character c (e.g. 'A')
 *  Output: converted 4-bit value (e.g. 0xA)
 */
char charToHex(char c)
{
	char hex = c;

	if (hex >= '0' && hex <= '9')
		hex -= '0';
	else if (hex >= 'A' && hex <= 'F')
	{
		hex -= 'A';
		hex += 10;
	}
	else if (hex >= 'a' && hex <= 'f')
	{
		hex -= 'a';
		hex += 10;
	}
	return hex;
}

/** charsToHex
 *  Convert two characters to byte value it represents.
 *  Inputs must be 0-9, A-F, or a-f.
 *
 *  Input: two characters c1 and c2 (e.g. 'A' and '7')
 *  Output: converted byte value (e.g. 0xA7)
 */
char charsToHex(char c1, char c2)
{
	char hex1 = charToHex(c1);
	char hex2 = charToHex(c2);
	return (hex1 << 4) + hex2;
}

void subBytes(unsigned char * byte_sub) {

	int i;

	for(i = 0; i < MAT_SIZE; ++i) {
		byte_sub[i] = aes_sbox[byte_sub[i]];
	}
}

void subWord(unsigned char * word_sub) {

	int i;

	for(i = 0; i < WORD_MAT; ++i) {
		word_sub[i] = aes_sbox[word_sub[i]];
 	}

}

void shiftRows(unsigned char * byte_shift) {

	unsigned char holder[MAT_SIZE];
	int i;

	holder[1] = byte_shift[5];
	holder[2] = byte_shift[10];
	holder[3] = byte_shift[15];

	holder[5] = byte_shift[9];
	holder[6] = byte_shift[14];
	holder[7] = byte_shift[3];

	holder[9] = byte_shift[13];
	holder[10] = byte_shift[2];
	holder[11] = byte_shift[7];

	holder[13] = byte_shift[1];
	holder[14] = byte_shift[6];
	holder[15] = byte_shift[11];

	for(i = 1; i < MAT_SIZE; ++i) {

		if(i % 4 != 0) {
			byte_shift[i] = holder[i];
		}

	}

}

void mixColumns(unsigned char * byte_mix) {

	unsigned char holder[MAT_SIZE];
	int i;

	for(i = 0; i < MAT_SIZE; i += 4) {

		holder[i] = byte_mix[i + 2] ^ byte_mix[i + 3] ^ gf_mul[byte_mix[i]][0] ^ gf_mul[byte_mix[i + 1]][1];
		holder[i + 1] = byte_mix[i] ^ byte_mix[i + 3] ^ gf_mul[byte_mix[i + 1]][0] ^ gf_mul[byte_mix[i + 2]][1];
		holder[i + 2] = byte_mix[i] ^ byte_mix[i + 1] ^ gf_mul[byte_mix[i + 2]][0] ^ gf_mul[byte_mix[i + 3]][1];
		holder[i + 3] = byte_mix[i + 1] ^ byte_mix[i + 2] ^ gf_mul[byte_mix[i + 3]][0] ^ gf_mul[byte_mix[i]][1];

	}

	for(i = 0; i < MAT_SIZE; ++i) {
		byte_mix[i] = holder[i];
	}

	// holder[0] = byte_mix[2] ^ byte_mix[3] ^ gf_mul[byte_mix[0]][0] ^ gf_mul[byte_mix[1]][1];
	// holder[1] = byte_mix[0] ^ byte_mix[3] ^ gf_mul[byte_mix[1]][0] ^ gf_mul[byte_mix[2]][1];
	// holder[2] = byte_mix[0] ^ byte_mix[1] ^ gf_mul[byte_mix[2]][0] ^ gf_mul[byte_mix[3]][1];
	// holder[3] = byte_mix[1] ^ byte_mix[2] ^ gf_mul[byte_mix[3]][0] ^ gf_mul[byte_mix[0]][1];
	//
	// holder[4] = byte_mix[6] ^ byte_mix[7] ^ gf_mul[byte_mix[4]][0] ^ gf_mul[byte_mix[5]][1];
	// holder[5] = byte_mix[4] ^ byte_mix[7] ^ gf_mul[byte_mix[5]][0] ^ gf_mul[byte_mix[6]][1];
	// holder[6] = byte_mix[4] ^ byte_mix[5] ^ gf_mul[byte_mix[6]][0] ^ gf_mul[byte_mix[7]][1];
	// holder[7] = byte_mix[5] ^ byte_mix[6] ^ gf_mul[byte_mix[7]][0] ^ gf_mul[byte_mix[4]][1];
	//
	// holder[8] = byte_mix[10] ^ byte_mix[11] ^ gf_mul[byte_mix[8]][0] ^ gf_mul[byte_mix[9]][1];
	// holder[9] = byte_mix[8] ^ byte_mix[11] ^ gf_mul[byte_mix[9]][0] ^ gf_mul[byte_mix[10]][1];
	// holder[10] = byte_mix[8] ^ byte_mix[9] ^ gf_mul[byte_mix[10]][0] ^ gf_mul[byte_mix[11]][1];
	// holder[11] = byte_mix[9] ^ byte_mix[10] ^ gf_mul[byte_mix[11]][0] ^ gf_mul[byte_mix[8]][1];
	//
	// holder[12] = byte_mix[14] ^ byte_mix[15] ^ gf_mul[byte_mix[12]][0] ^ gf_mul[byte_mix[13]][1];
	// holder[13] = byte_mix[12] ^ byte_mix[15] ^ gf_mul[byte_mix[13]][0] ^ gf_mul[byte_mix[14]][1];
	// holder[14] = byte_mix[12] ^ byte_mix[13] ^ gf_mul[byte_mix[14]][0] ^ gf_mul[byte_mix[15]][1];
	// holder[15] = byte_mix[13] ^ byte_mix[14] ^ gf_mul[byte_mix[15]][0] ^ gf_mul[byte_mix[12]][1];

}

void addRoundKey(unsigned char * byte_val, unsigned char * byte_schedule) {

	int i;
	for(i = 0; i < MAT_SIZE; ++i) {
		byte_val[i] ^= byte_schedule[i];
	}

}

void rotWord(unsigned char * byte_rot) {

	unsigned char holder;
	int i;

	holder = byte_rot[0];

	for(i = 0; i < 3; ++i) {
		byte_rot[i] = byte_rot[i + 1];
	}

	byte_rot[3] = holder;

}

void keyExpansion(unsigned char * key_val, unsigned char * key_sched) {

	unsigned char holder[WORD_MAT];
	unsigned char shifted_rCon;
	int i; int j;

	for(i = 0; i < MAT_SIZE; ++i) {
		key_sched[i] = key_val[i];
	}

	i = MAT_SIZE;
	while(i < (MAT_SIZE * 11)) {

		for(j = 0; j < WORD_MAT; ++j) {
			holder[j] = key_sched[i + j - WORD_MAT];
		}

		if(i % MAT_SIZE == 0) {
			rotWord(holder);
			subWord(holder);
			shifted_rCon = Rcon[i/MAT_SIZE] >> THREE_SHIFT;
			holder[0] ^= shifted_rCon;
		}

		for(j = 0; j < WORD_MAT; ++j) {
			key_sched[i] = key_sched[i - MAT_SIZE] ^ holder[j];
			++i;
		}
	}
}

void pack_message(unsigned char * msg_holder, unsigned char * key_holder, unsigned int * msg_enc, unsigned int * key) {

	int i; int offset;

	for(i = 0; i < WORD_MAT; ++i) {
		offset = i * WORD_MAT;

		msg_enc[i] = (msg_holder[offset] << THREE_SHIFT) + (msg_holder[offset + 1] << TWO_SHIFT) + (msg_holder[offset + 2] << ONE_SHIFT) + (msg_holder[offset + 3]);
		key[i] = (key_holder[offset] << THREE_SHIFT) + (key_holder[offset + 1] << TWO_SHIFT) + (key_holder[offset + 2] << ONE_SHIFT) + (key_holder[offset + 3]);
	}

}
/** encrypt
 *  Perform AES encryption in software.
 *
 *  Input: msg_ascii - Pointer to 32x 8-bit char array that contains the input message in ASCII format
 *         key_ascii - Pointer to 32x 8-bit char array that contains the input key in ASCII format
 *  Output:  msg_enc - Pointer to 4x 32-bit int array that contains the encrypted message
 *               key - Pointer to 4x 32-bit int array that contains the input key
 */
void encrypt(unsigned char * msg_ascii, unsigned char * key_ascii, unsigned int * msg_enc, unsigned int * key)
{
	// Implement this function

	unsigned char msg_holder[MAT_SIZE];
	unsigned char key_holder[MAT_SIZE];
	unsigned char key_sched[SCHED_SIZE];

	int i; int start; int end; int offset;

	for(i = 0; i < MAT_SIZE; ++i) {
		start = i * 2; end = start + 1;
		msg_holder[i] = charsToHex(msg_ascii[start], msg_ascii[end]);
		key_holder[i] = charsToHex(key_ascii[start], key_ascii[end]);
	}

	keyExpansion(key_holder, key_sched);

	printf("Key Expansion: \n");
	for(i = 0; i < MAT_SIZE * 11; ++i) {

		if(i % WORD_MAT == 0) {
			printf("\n");
		}

		printf("%x", key_sched[i]);
	}
	printf("\n");

	addRoundKey(msg_holder, key_sched);

	for(i = 1; i < NUM_ROUNDS; ++i) {
		offset = i * MAT_SIZE;

		subBytes(msg_holder);
		shiftRows(msg_holder);
		mixColumns(msg_holder);
		addRoundKey(msg_holder, key_sched + offset);
	}

	offset = NUM_ROUNDS * MAT_SIZE;
	subBytes(msg_holder);
	shiftRows(msg_holder);
	addRoundKey(msg_holder, key_sched + offset);

	printf("Last Round Key Added: \n");
	for(i = 0; i < MAT_SIZE; ++i) {
		printf("%x", key_sched[i + offset]);
	}
	printf("\n");

	pack_message(msg_holder, key_holder, msg_enc, key);
	// for(i = 0; i < WORD_MAT; ++i) {
	// 	offset = i * WORD_MAT;
	//
	// 	msg_enc[i] = (msg_holder[offset] << THREE_SHIFT) + (msg_holder[offset + 1] << TWO_SHIFT) + (msg_holder[offset + 2] << ONE_SHIFT) + (msg_holder[offset + 3]);
	// 	key[i] = (key_holder[offset] << THREE_SHIFT) + (key_holder[offset + 1] << TWO_SHIFT) + (key_holder[offset + 2] << ONE_SHIFT) + (key_holder[offset + 3]);
	// }

}

/** decrypt
 *  Perform AES decryption in hardware.
 *
 *  Input:  msg_enc - Pointer to 4x 32-bit int array that contains the encrypted message
 *              key - Pointer to 4x 32-bit int array that contains the input key
 *  Output: msg_dec - Pointer to 4x 32-bit int array that contains the decrypted message
 */
void decrypt(unsigned int * msg_enc, unsigned int * msg_dec, unsigned int * key)
{
	// Implement this function
}

/** main
 *  Allows the user to enter the message, key, and select execution mode
 *
 */
int main()
{
	// Input Message and Key as 32x 8-bit ASCII Characters ([33] is for NULL terminator)
	unsigned char msg_ascii[33];
	unsigned char key_ascii[33];
	// Key, Encrypted Message, and Decrypted Message in 4x 32-bit Format to facilitate Read/Write to Hardware
	unsigned int key[4];
	unsigned int msg_enc[4];
	unsigned int msg_dec[4];

	printf("Select execution mode: 0 for testing, 1 for benchmarking: ");
	scanf("%d", &run_mode);

	if (run_mode == 0) {
		// Continuously Perform Encryption and Decryption
		while (1) {
			int i = 0;
			printf("\nEnter Message:\n");
			scanf("%s", msg_ascii);
			printf("\n");
			printf("\nEnter Key:\n");
			scanf("%s", key_ascii);
			printf("\n");
			encrypt(msg_ascii, key_ascii, msg_enc, key);
			printf("\nEncrpted message is: \n");
			for(i = 0; i < 4; i++){
				printf("%08x", msg_enc[i]);
			}

			for(i = 0; i < WORD_MAT; ++i) {
					AES_PTR[i] = key[i];
			}

			AES_PTR[10] = 0xDEADBEEF;
			if(AES_PTR[10] != 0xDEADBEEF) {
					printf("Error!");
			}

			printf("\n");
			decrypt(msg_enc, msg_dec, key);
			printf("\nDecrypted message is: \n");
			for(i = 0; i < 4; i++){
				printf("%08x", msg_dec[i]);
			}
			printf("\n");
		}
	}
	else {
		// Run the Benchmark
		int i = 0;
		int size_KB = 2;
		// Choose a random Plaintext and Key
		for (i = 0; i < 32; i++) {
			msg_ascii[i] = 'a';
			key_ascii[i] = 'b';
		}
		// Run Encryption
		clock_t begin = clock();
		for (i = 0; i < size_KB * 64; i++)
			encrypt(msg_ascii, key_ascii, msg_enc, key);
		clock_t end = clock();
		double time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
		double speed = size_KB / time_spent;
		printf("Software Encryption Speed: %f KB/s \n", speed);
		// Run Decryption
		begin = clock();
		for (i = 0; i < size_KB * 64; i++)
			decrypt(msg_enc, msg_dec, key);
		end = clock();
		time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
		speed = size_KB / time_spent;
		printf("Hardware Encryption Speed: %f KB/s \n", speed);
	}
	return 0;
}
