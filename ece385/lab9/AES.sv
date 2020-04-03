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
logic [31:0] mc_input, mc_middle; // input, middle values for mix columns

// 0 - round, 1 - key, 2 - instruction, 3 - mix columns
logic [3:0] c_add_bool, c_reset; // adder indicator, reset for counter
// logic [4:0] round [3:0]; // counter output (4 5 bit round counts)
logic [4:0] key_round;
logic [3:0] num_round;
logic [3:0] instr_round;
logic [3:0] mc_round;

logic load_key, load_aes_enc; // first shift register - load first msg or curr msg (first RK?)
logic [127:0] cur_instruction; // from MUX selecting between 4 loop actions

logic [1407:0] total_key_sched;

// NEED:  (two 4:1 MUX 32 & 128b), 1:4 decoder, AES_CONTROL
SR_128b key_SR( .Clk(CLK), .Reset(RESET), .Load1(load_key), .Load2(load_aes_enc),
				   .data_1(cur_instruction), .data_2(AES_MSG_ENC), .Data_Out(cur_msg) );

// KEY EXPANSION
counter_key k_counter( .CLK(CLK), .RESET(c_reset[1]), .add_bool(c_add_bool[1]), .count_out(key_round) );
KeyExpansion key_exp_INV( .clk(CLK), .Cipherkey(AES_KEY), .KeySchedule(total_key_sched) );

// invShiftRows
InvShiftRows shift_rows_INV( .data_in(cur_msg), .data_out(output_isr) );

// InvSubBytes
InvSub_16 sub_16_INV ( .clk(CLK), .in(cur_msg), .out(output_isb) );

// ADD ROUND KEY
counter r_counter( .*, .RESET(c_reset[0]), .add_bool(c_add_bool[0]), .count_total(4'd10), .count_out(num_round) );
addRoundKey add_round_key_INV( .*, .round_num(num_round), .msg_output(output_ark) );

counter mc_counter( .*, .RESET(c_reset[3]), .add_bool(c_add_bool[3]), .count_total(4'd4), .count_out(mc_round) ); // mix column counter

// MIX COLUMNS
MUX_41 #(32) word_selector ( .in0(cur_msg[31:0]), .in1(cur_msg[63:32]),
							 .in2(cur_msg[95:64]), .in3(cur_msg[127:96]),
							 .select(mc_round), .mux_output(mc_input) ); // 32 b MUX

InvMixColumns mix_col_INV( .in(mc_input), .out(mc_middle) );
selector imc_sel( .*, .out_bits(mc_middle), .select(mc_round), .data_out(output_imc) );



counter ic_counter( .*, .RESET(c_reset[2]), .add_bool(c_add_bool[2]), .count_total(5'd4), .count_out(instr_round) ); // instruction counter

MUX_41 #(128) instr_selector ( .in0(output_imc), .in1(output_ark),
							 .in2(output_isb), .in3(output_isr),
							 .select(instr_round), .mux_output(cur_instruction) ); // 128 b MUX

control_unit state_machine( .*, .round_reset(c_reset), .round_bool(c_add_bool),
									 .Load_R0(load_aes_enc), .Load_Key(load_key) );


assign AES_MSG_DEC = cur_msg;

endmodule
