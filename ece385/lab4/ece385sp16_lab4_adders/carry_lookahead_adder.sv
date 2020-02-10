module carry_lookahead_adder
(
    input   logic[15:0]     A,
    input   logic[15:0]     B,
    output  logic[15:0]     Sum,
    output  logic           CO
);

    /* TODO
     *
     * Insert code here to implement a CLA adder.
     * Your code should be completly combinational (don't use always_ff or always_latch).
     * Feel free to create sub-modules or other files. */
	  
	  logic [4:0] C;
	  logic [3:0] G;
	  logic [3:0] P;
	  
	  always_comb begin
	  
			for(int i = 1; i < 5; i++) begin
				C[i] = G[i-1] | (P[i-1] & C[i-1]);
			end
			C[0] = 0;
			CO = C[4];
	  end
	  
	  four_bit_rip FBR0(.x(A[3:0]), .y(B[3:0]), .cin(0), .sum(Sum[3:0]), .g_last(G[0]), .p_last(P[0]));
	  four_bit_rip FBR1(.x(A[7:4]), .y(B[7:4]), .cin(C[1]), .sum(Sum[7:4]), .g_last(G[1]), .p_last(P[1]));
	  four_bit_rip FBR2(.x(A[11:8]), .y(B[11:8]), .cin(C[2]), .sum(Sum[11:8]), .g_last(G[2]), .p_last(P[2]));
	  four_bit_rip FBR3(.x(A[15:12]), .y(B[15:12]), .cin(C[3]), .sum(Sum[15:12]), .g_last(G[3]), .p_last(P[3]));
	  
endmodule

module four_bit_rip
(
			input[3:0] x,
			input[3:0] y,
			input cin,
			output logic [3:0] sum,
			output logic g_last,
			output logic p_last
);

logic [3:0] C;
logic [3:0] G;
logic [3:0] P;

always_comb begin

	C[0] = cin;

	P = x ^ y;
	G = x & y;
	
	for(int i = 1; i < 4; i++) begin
		C[i] = G[i-1] | (P[i-1] & C[i-1]);
	end
	
end

assign g_last = (G[3]) | (G[2] & P[3]) | (G[1] & P[3] & P[2]) | (G[0] & P[3] & P[2] & P[1]);
assign p_last = P[3] & P[2] & P[1] & P[0];

full_adder f0(.x(x[0]), .y(y[0]), .cin(C[0]), .sum(sum[0]));
full_adder f1(.x(x[1]), .y(y[1]), .cin(C[1]), .sum(sum[1]));
full_adder f2(.x(x[2]), .y(y[2]), .cin(C[2]), .sum(sum[2]));
full_adder f3(.x(x[3]), .y(y[3]), .cin(C[3]), .sum(sum[3]));

endmodule
	
