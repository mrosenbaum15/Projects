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


// 0 - round, 1 - key, 2 - instruction, 3 - mix columns
logic [3:0] c_add_bool, c_reset; // adder indicator, reset for counter
logic [4:0] round [3:0]; // counter output (4 5 bit round counts)

logic load_key, load_aes_enc; // first shift register - load first msg or curr msg
logic curr_instruction; // from MUX selecting between 4 loop actions

logic [1407:0] total_key_sched;

// NEED: invMixCol (two 4:1 MUX 32 & 128b), 1:4 decoder, AES_CONTROL
128bit_reg key_SR( .Clk(CLK), .Reset(RESET), .Load1(load_key), .Load2(load_aes_enc),
				   .data_1(curr_instruction), .data_2(AES_MSG_ENC), .Data_Out(cur_msg) );

// KEY EXPANSION
counter k_counter( .*, RESET(c_reset[1]), add_bool(c_add_bool[1]), .counter_type(2'd1), .count_out(round[1]) );
KeyExpansion key_exp_INV( .clk(CLK), .Cipherkey(AES_KEY), .KeySchedule(total_key_sched) );

// ADD ROUND KEY
counter r_counter( .*, .RESET(c_reset[0]), .add_bool(c_add_bool[0]), .counter_type(2'd0), .count_out(round[0]) );
addRoundKey add_round_key_INV( .*, .round_num(cur_round), .msg_out(output_ark) );

input  logic clk,
input  logic [127:0]  Cipherkey,
output logic [1407:0] KeySchedule

// invShiftRows
InvShiftRows shift_rows_INV( .data_in(cur_msg), .data_out(output_isr) );

// InvSubBytes
InvSub_16 sub_16_INV ( .clk(CLK), .in(cur_msg), .out(output_isb) );

counter ic_counter( .*, .RESET(c_reset[2]), .add_bool(c_add_bool[2]), .counter_type(2'd2), .count_out(round[2]) ); // instruction counter

counter mc_counter( .*, .RESET(c_reset[3]), .add_bool(c_add_bool[3]), .counter_type(2'd2), .count_out(round[3]) ); // mix column counter

// MIX COLUMNS

endmodule
