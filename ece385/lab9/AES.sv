/************************************************************************
AES Decryption Core Logic

Dong Kai Wang, Fall 2017

For use with ECE 385 Experiment 9
University of Illinois ECE Department
************************************************************************/

module AES (
	input	 logic CLK,
	input  logic RESET,
	input  logic AES_START,
	output logic AES_DONE,
	input  logic [127:0] AES_KEY,
	input  logic [127:0] AES_MSG_ENC,
	output logic [127:0] AES_MSG_DEC
);

logic [127:0] cur_msg, output_isr, output_isb, output_ark, output_imc;
logic cur_round, rc_add_bool, reset_rc; // round counter output, round counter adder indicator, reset for round counter
logic [1407:0] total_key_sched;

// NEED: 128 bit SR, keyExpansion + keyCounter, invMixCol (two 4:1 MUX, counter with it), extra counter, 4:1 MUX, AES_CONTROL

// ADD ROUND KEY
round_counter r_counter( .*, .RESET(reset_rc), .add_bool(rc_add_bool), .count_out(cur_round) );
addRoundKey add_round_key( .*, .round_num(cur_round), .msg_out(output_ark) );

// KEY EXPANSION

// invShiftRows
InvShiftRows shift_rows_INV( .data_in(cur_msg), .data_out(output_isr) );

// InvSubBytes
InvSub_16 sub_16_INV ( .clk(CLK), .in(cur_msg), .out(output_isb) );

// MIX COLUMNS


endmodule
