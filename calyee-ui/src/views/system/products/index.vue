<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="产品名称" prop="productName">
        <el-input
          v-model="queryParams.productName"
          placeholder="请输入产品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="库存单位eg: DP-992882" prop="sku">
        <el-input
          v-model="queryParams.sku"
          placeholder="请输入库存单位eg: DP-992882"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品类别ID" prop="categoryId">
        <el-input
          v-model="queryParams.categoryId"
          placeholder="请输入产品类别ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录创建用户" prop="createUser">
        <el-input
          v-model="queryParams.createUser"
          placeholder="请输入记录创建用户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录更新用户" prop="updateUser">
        <el-input
          v-model="queryParams.updateUser"
          placeholder="请输入记录更新用户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="删除标记，0表示未删除，1表示已删除" prop="deleteFlag">
        <el-input
          v-model="queryParams.deleteFlag"
          placeholder="请输入删除标记，0表示未删除，1表示已删除"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:products:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:products:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:products:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:products:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="productsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="产品唯一标识符" align="center" prop="productId" />
      <el-table-column label="产品名称" align="center" prop="productName" />
      <el-table-column label="产品描述" align="center" prop="description" />
      <el-table-column label="库存单位eg: DP-992882" align="center" prop="sku" />
      <el-table-column label="产品类别ID" align="center" prop="categoryId" />
      <el-table-column label="记录创建用户" align="center" prop="createUser" />
      <el-table-column label="记录更新用户" align="center" prop="updateUser" />
      <el-table-column label="删除标记，0表示未删除，1表示已删除" align="center" prop="deleteFlag" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:products:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:products:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改产品，存储产品信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="产品名称" prop="productName">
          <el-input v-model="form.productName" placeholder="请输入产品名称" />
        </el-form-item>
        <el-form-item label="产品描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="库存单位eg: DP-992882" prop="sku">
          <el-input v-model="form.sku" placeholder="请输入库存单位eg: DP-992882" />
        </el-form-item>
        <el-form-item label="产品类别ID" prop="categoryId">
          <el-input v-model="form.categoryId" placeholder="请输入产品类别ID" />
        </el-form-item>
        <el-form-item label="记录创建用户" prop="createUser">
          <el-input v-model="form.createUser" placeholder="请输入记录创建用户" />
        </el-form-item>
        <el-form-item label="记录更新用户" prop="updateUser">
          <el-input v-model="form.updateUser" placeholder="请输入记录更新用户" />
        </el-form-item>
        <el-form-item label="删除标记，0表示未删除，1表示已删除" prop="deleteFlag">
          <el-input v-model="form.deleteFlag" placeholder="请输入删除标记，0表示未删除，1表示已删除" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listProducts, getProducts, delProducts, addProducts, updateProducts } from "@/api/system/products";

export default {
  name: "Products",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 产品，存储产品信息表格数据
      productsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        productName: null,
        description: null,
        sku: null,
        categoryId: null,
        createUser: null,
        updateUser: null,
        deleteFlag: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        productName: [
          { required: true, message: "产品名称不能为空", trigger: "blur" }
        ],
        sku: [
          { required: true, message: "库存单位eg: DP-992882不能为空", trigger: "blur" }
        ],
        createUser: [
          { required: true, message: "记录创建用户不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "记录创建时间不能为空", trigger: "blur" }
        ],
        deleteFlag: [
          { required: true, message: "删除标记，0表示未删除，1表示已删除不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询产品，存储产品信息列表 */
    getList() {
      this.loading = true;
      listProducts(this.queryParams).then(response => {
        this.productsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        productId: null,
        productName: null,
        description: null,
        sku: null,
        categoryId: null,
        createUser: null,
        createTime: null,
        updateUser: null,
        updateTime: null,
        deleteFlag: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.productId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加产品，存储产品信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const productId = row.productId || this.ids
      getProducts(productId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改产品，存储产品信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.productId != null) {
            updateProducts(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProducts(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const productIds = row.productId || this.ids;
      this.$modal.confirm('是否确认删除产品，存储产品信息编号为"' + productIds + '"的数据项？').then(function() {
        return delProducts(productIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/products/export', {
        ...this.queryParams
      }, `products_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
