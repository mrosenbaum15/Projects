//io_handler.c
#include "io_handler.h"
#include <stdio.h>

void IO_init(void)
{
	*otg_hpi_reset = 1;
	*otg_hpi_cs = 1;
	*otg_hpi_r = 1;
	*otg_hpi_w = 1;
	*otg_hpi_address = 0;
	*otg_hpi_data = 0;
	// Reset OTG chip
	*otg_hpi_cs = 0;
	*otg_hpi_reset = 0;
	*otg_hpi_reset = 1;
	*otg_hpi_cs = 1;
}

/*
 * This function writes data the location in memory that is determined by an address.
 *
 * @param Address - the location to write data
 * @param Data - the data to write
 *
 * @return none
 *
 * @note none
 */
void IO_write(alt_u8 Address, alt_u16 Data)
{
//*************************************************************************//
//									TASK								   //
//*************************************************************************//
//							Write this function							   //
//*************************************************************************//
	// writing address to hpi_address
	*(otg_hpi_address) = Address;
	*(otg_hpi_cs) = 0;
	*(otg_hpi_w) = 0;

	// storing Data to Address
	*(otg_hpi_data) = Data;
	*(otg_hpi_cs) = 1;
	*(otg_hpi_w) = 1;

}

/*
 * This function reads and returns data from the location in memory that is determined by an address.
 *
 * @param Address - the location to read data
 *
 * @return the data found at Address
 *
 * @note none
 */
alt_u16 IO_read(alt_u8 Address)
{
	alt_u16 temp;
//*************************************************************************//
//									TASK								   //
//*************************************************************************//
//							Write this function							   //
//*************************************************************************//
	//printf("%x\n",temp);

	// writing address to hpi_address
	*(otg_hpi_address) = Address;
	*(otg_hpi_cs) = 0;
	*(otg_hpi_r) = 0;

	// reading from the data at Address
	temp = *(otg_hpi_data);
	*(otg_hpi_cs) = 1;
	*(otg_hpi_r) = 1;

	// returning the data found
	return temp;
}
