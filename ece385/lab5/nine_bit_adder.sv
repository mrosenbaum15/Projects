// a nine bit adder - inputs and outputs based off diagram given in lab5 manual
// inputs: A_in, B_in (8 bit arrays), func_val
// outputs: S (9 bit array - a sum)
module nine_bit_adder 
(
        input [7:0] A_in,
		  input [7:0] B_in,
		  input func_val,
		  output [8:0] S
);

logic [1:0] last_bits; // corresponses to A[7] and B[7], respectively
logic [7:0] carry_bits; // 8 carry bits, one for each adder
logic [7:0] new_B; // for computation of B

always_comb begin

        new_B = ( B_in ^ { 8{func_val} } ); // shift computation of B
        last_bits[0] = A_in[7]; // last bit of A
        last_bits[1] = new_B[7]; // last bit of B

end

// creating 8 full adders
full_adder full_0(.x(A_in[0]), .y(new_B[0]), .cin(func_val), .sum(S[0]), .cout(carry_bits[0]));
full_adder full_1(.x(A_in[1]), .y(new_B[1]), .cin(carry_bits[0]), .sum(S[1]), .cout(carry_bits[1]));
full_adder full_2(.x(A_in[2]), .y(new_B[2]), .cin(carry_bits[1]), .sum(S[2]), .cout(carry_bits[2]));
full_adder full_3(.x(A_in[3]), .y(new_B[3]), .cin(carry_bits[2]), .sum(S[3]), .cout(carry_bits[3]));
full_adder full_4(.x(A_in[4]), .y(new_B[4]), .cin(carry_bits[3]), .sum(S[4]), .cout(carry_bits[4]));
full_adder full_5(.x(A_in[5]), .y(new_B[5]), .cin(carry_bits[4]), .sum(S[5]), .cout(carry_bits[5]));
full_adder full_6(.x(A_in[6]), .y(new_B[6]), .cin(carry_bits[5]), .sum(S[6]), .cout(carry_bits[6]));
full_adder full_7(.x(A_in[7]), .y(new_B[7]), .cin(carry_bits[6]), .sum(S[7]), .cout(carry_bits[7]));
full_adder full_8(.x(last_bits[0]), .y(last_bits[1]), .cin(carry_bits[7]), .sum(S[8]));


endmodule

// based off given code in lab 4 manual
module full_adder
(
			input x,
			input y,
			input cin,
			output logic sum,
			output logic cout
);


assign sum = x ^ y ^ cin;
assign cout = (x&y) | (y&cin) | (x&cin);

endmodule
