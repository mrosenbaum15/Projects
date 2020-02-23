module branch_enable
(
        input logic Clk,
        input logic Reset,
        input logic load_cc,
        input logic load_branch_en,
        input logic [15:0] input_data,
        input logic [2:0] instructions,
        output logic branch_en_output
);

logic [2:0] nzp;
logic [2:0] output_nzp;

always_ff @ (posedge Clk) begin

        if(load_branch_en)
            branch_en_output <= ~( (instructions & output_nzp) == 3'b000);
        else if(Reset)
            branch_enable <= 0;

        if(load_cc) begin
            output_nzp[0] <= nzp[0];
            output_nzp[1] <= nzp[1];
            output_nzp[2] <= nzp[2];
        end
end

always_comb begin

        if(input_data == 16'b0) begin
            nzp[0] = 0;
            nzp[1] = 1;
            nzp[2] = 0;
        end
        else if(input_data[15] == 0) begin
            nzp[0] = 0;
            nzp[1] = 0;
            nzp[2] = 1;
        end
        else if(input_data[15] == 1) begin
            nzp[0] = 1;
            nzp[1] = 0;
            nzp[2] = 0;
        end
        else begin
            nzp[0] = Z;
            nzp[1] = Z;
            nzp[2] = Z;
        end

end

endmodule
