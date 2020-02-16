module nine_bit_adder
(
        input [7:0] A,
        input [7:0] B,
        input function,
        output [7:0] S
);

logic [1:0] last_bits;
logic [7:0] carry;
logic [7:0] new_B;

always_comb begin

        new_B = ( B ^ { 8{function} } );
        last_bits[0] = A[7];
        last_bits[1] = new_B[7];

end

full_adder full_0(.x(A[0]), .y(new_B[0]), .cin(function), .sum(S[0]), .cout(carry[0]));
full_adder full_1(.x(A[1]), .y(new_B[1]), .cin(carry[0]), .sum(S[1]), .cout(carry[1]));
full_adder full_2(.x(A[2]), .y(new_B[2]), .cin(carry[1]), .sum(S[2]), .cout(carry[2]));
full_adder full_3(.x(A[3]), .y(new_B[3]), .cin(carry[2]), .sum(S[3]), .cout(carry[3]));
full_adder full_4(.x(A[4]), .y(new_B[4]), .cin(carry[3]), .sum(S[4]), .cout(carry[4]));
full_adder full_5(.x(A[5]), .y(new_B[5]), .cin(carry[4]), .sum(S[5]), .cout(carry[5]));
full_adder full_6(.x(A[6]), .y(new_B[6]), .cin(carry[5]), .sum(S[6]), .cout(carry[6]));
full_adder full_7(.x(A[7]), .y(new_B[7]), .cin(carry[6]), .sum(S[7]), .cout(carry[7]));
full_adder full_8(.x(last_bits[0]), .y(last_bits[1]), .cin(carry[7]), .sum(S[8]));


endmodule
