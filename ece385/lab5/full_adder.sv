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
