/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.pdfbox.examples.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 * Decode a base64 encoded file and save the result as a PDF.
 */
public final class Base64ToPDF
{
    private Base64ToPDF()
    {
        // example class should not be instantiated
    }

    /**
     * Decodes a base64 text file to a PDF file.
     *
     * @param base64File file containing base64 encoded PDF
     * @param pdfFile destination PDF file
     *
     * @throws IOException if the file could not be read or written
     */
    public static void convert(File base64File, File pdfFile) throws IOException
    {
        String base64 = new String(Files.readAllBytes(base64File.toPath()), StandardCharsets.ISO_8859_1);
        byte[] decoded = Base64.getDecoder().decode(base64);
        try (PDDocument document = Loader.loadPDF(decoded))
        {
            document.save(pdfFile);
        }
    }

    /**
     * Command line interface.
     *
     * @param args the command line arguments
     *
     * @throws IOException if an I/O error occurs
     */
    public static void main(String[] args) throws IOException
    {
        if (args.length != 2)
        {
            System.err.println("usage: java " + Base64ToPDF.class.getName()
                    + " <base64-input-file> <pdf-output-file>");
            System.exit(1);
        }
        convert(new File(args[0]), new File(args[1]));
    }
}
